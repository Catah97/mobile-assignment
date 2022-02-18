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
    val meters = context.getString(R.string.m)
    val textValue = "${value.roundToInt()}$meters"
    text = textValue
}

@BindingAdapter("setTonsText")
fun TextView.setTonsText(value: Double) {
    val tons = context.getString(R.string.t)
    val tonsValue = value.roundToInt() / 1000
    val textValue = "$tonsValue$tons"
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
    val engines = context.getString(R.string.engines)
    val textValue = "$enginesCount $engines"
    text = textValue
}

@BindingAdapter("setTonsOfFuelText")
fun TextView.setTonsOfFuelText(tonsOfFuel: Double) {
    val describe = context.getString(R.string.tons_of_fuel)
    val textValue = "${tonsOfFuel.roundToInt()} $describe"
    text = textValue
}

@BindingAdapter("setSecondBurnTimeText")
fun TextView.setSecondBurnTimeText(secondBurnTimeText: Int) {
    val describe = context.getString(R.string.seconds_burn_time)
    val textValue = "$secondBurnTimeText $describe"
    text = textValue
}