package com.example.rocketapp.compose.rocket.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketapp.R
import com.example.rocketapp.rocket.list.RocketListViewModel
import com.example.rocketapp.rocket.list.adapter.RocketItem
import com.example.rocketapp.tools.date.toUiDate
import java.util.*

@Composable
fun RocketListScreen(viewModel: RocketListViewModel) {
    val rocketList = viewModel.rocketItemsData.collectAsState().value
    LazyColumn {
        items(rocketList) {
            RocketListRow(it)
        }
    }
}

@Composable
fun RocketListRow(rocketItem: RocketItem) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            RocketImage()
            Spacer(modifier = Modifier.width(8.dp))
            RocketInfo(rocketItem)
        }
        Divider(color = Color.Black, thickness = 1.dp)
    }
}

@Composable
fun RocketImage() {
    val size = dimensionResource(R.dimen.rocket_icon_size)
    val rocketImage = painterResource(R.drawable.ic_rocket)
    Image(
        painter = rocketImage,
        contentDescription = "Rocket",
        modifier = Modifier
            .width(size)
            .height(size)
    )
}

@Composable
fun RocketInfo(rocketItem: RocketItem) {
    Column(
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            rocketItem.name,
            fontSize = 20.sp
        )
        Text(
            rocketItem.firstFlight.toUiDate(),
            fontSize = 14.sp
        )
    }
}

//@Preview
//@Composable
//fun ComposablePreview() {
//    RocketListScreen()
//}