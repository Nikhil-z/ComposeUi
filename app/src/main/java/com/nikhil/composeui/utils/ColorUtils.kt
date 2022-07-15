package com.nikhil.composeui.utils

import androidx.compose.ui.graphics.Color


/**
 * @Author: Nikhil
 * @Date: 15,July,2022
 */

fun randomColor(): Color {
    val generatedColor = android.graphics.Color.argb(
        255, (30..200).random(), (30..200).random(),
        (30..200).random()
    )
    return Color(generatedColor)
}