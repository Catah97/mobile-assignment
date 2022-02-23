package com.example.rocketapp.tools.binding

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.rocketapp.R
import kotlin.math.roundToInt

@BindingAdapter("setMetersText")
fun TextView.setMetersText(value: Double) {
    val mates = value.roundToInt()
    val textValue = context.getString(R.string.m, mates)
    text = textValue
}

@BindingAdapter("setTonsText")
fun TextView.setTonsText(value: Double) {
    val tonsValue = value.roundToInt() / 1000
    val textValue = context.getString(R.string.t, tonsValue)
    text = textValue
}

@BindingAdapter("setReusableText")
fun TextView.setTonsText(reusable: Boolean) {
    val textValue = if (reusable) {
        context.getString(R.string.reusable)
    } else {
        context.getString(R.string.not_reusable)
    }
    text = textValue
}

@BindingAdapter("setEnginesText")
fun TextView.setEnginesText(enginesCount: Int) {
    val textValue = context.getString(R.string.engines, enginesCount)
    text = textValue
}

@BindingAdapter("setTonsOfFuelText")
fun TextView.setTonsOfFuelText(tonsOfFuel: Double) {
    val tonsOfFuelValue = tonsOfFuel.roundToInt()
    val textValue = context.getString(R.string.tons_of_fuel, tonsOfFuelValue)
    text = textValue
}

@BindingAdapter("setSecondBurnTimeText")
fun TextView.setSecondBurnTimeText(secondBurnTimeText: Int) {
    val textValue = context.getString(R.string.seconds_burn_time, secondBurnTimeText)
    text = textValue
}