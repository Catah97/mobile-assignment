package com.example.rocketapp.compose.rocket.launch

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rocketapp.R
import com.example.rocketapp.compose.rocket.list.RocketListScreen
import com.example.rocketapp.rocket.launch.RocketLaunchStatus
import com.example.rocketapp.rocket.launch.RocketLaunchStatus.DONE
import com.example.rocketapp.rocket.launch.RocketLaunchStatus.IDLE
import com.example.rocketapp.rocket.launch.RocketLaunchStatus.LAUNCHING
import com.example.rocketapp.rocket.list.adapter.RocketItem
import java.util.*

@Composable
fun LaunchScreen(launchViewModel: RocketLaunchViewModel) {
    val state = launchViewModel.launchStatusData.collectAsState()
    val stateValue = state.value
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        RocketImage(launchViewModel, Modifier.weight(1F, false))
        RocketLaunchText(stateValue, Modifier.weight(1F, false))
    }
}


@Composable
fun RocketImage(launchViewModel: RocketLaunchViewModel, modifier: Modifier = Modifier) {
    val state = launchViewModel.launchStatusData.collectAsState().value
    val iconId = when(state) {
        IDLE -> R.drawable.ic_rocket_idle
        LAUNCHING, DONE -> R.drawable.ic_rocket_flying
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val offsetDp = when (state) {
        IDLE -> 0.dp
        LAUNCHING, DONE -> (-screenHeight)
    }
    val offsetAnimation: Dp by animateDpAsState(
        offsetDp,
        tween(
            5000,
            easing = FastOutLinearInEasing)
    )
    SideEffect {
        if (offsetAnimation == -screenHeight) {
            launchViewModel.launchDone()
        }
    }
    val painter = painterResource(iconId)
    Image(
        painter = painter,
        contentDescription = "Rocket",
        modifier = modifier
            .offset(y = offsetAnimation)
    )
}

@Composable
fun RocketLaunchText(status: RocketLaunchStatus, modifier: Modifier = Modifier) {
    val textValueId = when (status) {
        IDLE -> R.string.launch_rocket_info
        LAUNCHING, DONE -> R.string.launch_successful
    }
    val text = stringResource(textValueId)
    Text(text,
        fontSize = 16.sp,
        modifier = modifier
            .padding(bottom = 32.dp)
    )
}