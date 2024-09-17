package com.example.rocketapp.compose.rocket.detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketapp.R
import com.example.rocketapp.rocket.repository.model.Rocket
import com.example.rocketapp.rocket.repository.model.info.RocketDiameter
import com.example.rocketapp.rocket.repository.model.info.RocketHeight
import com.example.rocketapp.rocket.repository.model.info.RocketMass
import com.example.rocketapp.rocket.repository.model.stages.RocketFirstStage
import com.example.rocketapp.rocket.repository.model.stages.RocketSecondStage
import com.skydoves.landscapist.glide.GlideImage
import java.util.*
import kotlin.math.roundToInt

@Composable
fun RocketDetailScreen(rocket: Rocket?) {
    val scrollState = rememberScrollState()
    if (rocket != null) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            SectionTitle(R.string.overview)
            SectionText(rocket.description)
            SectionTitle(R.string.parameters)
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                RocketInfo(rocket.height.meters, R.string.m, R.string.height)
                RocketInfo(rocket.diameter.meters, R.string.m, R.string.diameter)
                RocketInfo(rocket.mass.kg, R.string.t, R.string.mass)
            }
            FirstRocketStage(rocket.firstStage)
            SecondRocketStage(rocket.secondStage)
            if (rocket.image.isNotEmpty()) {
                SectionTitle(R.string.photos)
                RocketImages(rocket.image)
            }
        }
    }
}

@Composable
fun SectionTitle(@StringRes id: Int) {
    val title = stringResource(id)
    Text(title, fontWeight = FontWeight.Bold)
}

@Composable
fun SectionText(text: String) {
    Text(text)
}

@Composable
fun RocketInfo(
    value: Double,
    @StringRes valueUnitId: Int,
    @StringRes titleId: Int
) {
    val mates = value.roundToInt()
    val textValue = stringResource(valueUnitId, mates)
    val title = stringResource(titleId)
    RocketInfo(value = textValue, title = title)
}

@Composable
fun RocketInfo(value: String, title: String) {
    val size = 80.dp
    val color = colorResource(R.color.primary_color)
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(size)
            .width(size)
            .background(color, RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Text(
            value,
            color = Color.White,
            fontSize = 16.sp,
            maxLines = 1,
        )
        Text(
            title,
            color = Color.White,
            fontSize = 12.sp,
            maxLines = 1
        )
    }
}

@Composable
fun FirstRocketStage(rocketFirstStage: RocketFirstStage) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = RocketStageModifier()
    ) {
        SectionTitle(R.string.first_stage)
        RocketStageContent(
            rocketFirstStage.reusable,
            rocketFirstStage.engines,
            rocketFirstStage.fuelAmountTons,
            rocketFirstStage.burnTimeSec
        )
    }
}

@Composable
fun SecondRocketStage(rocketSecondStage: RocketSecondStage) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = RocketStageModifier()
    ) {
        SectionTitle(R.string.second_stage)
        RocketStageContent(
            rocketSecondStage.reusable,
            rocketSecondStage.engines,
            rocketSecondStage.fuelAmountTons,
            rocketSecondStage.burnTimeSec
        )
    }
}

@Composable
fun RocketStageContent(
    reusable: Boolean,
    engines: Int,
    fuelAmountTons: Double,
    burnTimeSec: Int?
) {
    val reusableText = if (reusable) {
        stringResource(R.string.reusable)
    } else {
        stringResource(R.string.not_reusable)
    }
    val enginesText = stringResource(
        R.string.engines,
        engines
    )
    val tonsOfFuelText = stringResource(
        R.string.tons_of_fuel,
        fuelAmountTons.roundToInt()
    )
    val burnTime = burnTimeSec?.toString() ?: "-"
    val burnTimeText = stringResource(R.string.seconds_burn_time, burnTime)
    StageIcon(R.drawable.ic_reusable, reusableText)
    StageIcon(R.drawable.ic_engine, enginesText)
    StageIcon(R.drawable.ic_fuel, tonsOfFuelText)
    StageIcon(R.drawable.ic_burn, burnTimeText)
}

@Composable
fun StageIcon(@DrawableRes painId: Int, text: String) {
    val icon = painterResource(id = painId)
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Image(painter = icon, contentDescription = text)
        Text(text = text)
    }
}

@Composable
fun RocketStageModifier() = Modifier
    .background(
        colorResource(R.color.stage_background_color),
        RoundedCornerShape(10.dp)
    )
    .fillMaxWidth()
    .padding(8.dp)

@Composable
fun RocketImages(list: List<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        list.forEach { photoUrl ->
            GlideImage(
                imageModel = photoUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(
                    RoundedCornerShape(
                        10.dp
                    )
                )
            )
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    val rocket = Rocket(
        0,
        "Rocket",
        Date(),
        "Descripti alsdjkaskd ajka jksdhas dhasd aas dd ajsdj asdjlasd jklaon",
        RocketHeight(10.0, 11.0),
        RocketMass(11050.0, 11.0),
        RocketDiameter(11.0, 12.0),
        RocketFirstStage(
            true, 2, 10.0, null
        ),
        RocketSecondStage(
            false, 3, 50.0, 5
        ),
        listOf(
            "https://www.zoochleby.cz/uploads/images/6131/large/003372-6131.jpg"
        )
    )
    RocketDetailScreen(rocket = rocket)
}
