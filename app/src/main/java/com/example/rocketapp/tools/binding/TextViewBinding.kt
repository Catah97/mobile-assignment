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