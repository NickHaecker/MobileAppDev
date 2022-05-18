package com.example.bettergithubmobile.ui.theme

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable



private val DarkColorPalette = darkColors(

)
private val LightColorPalette = lightColors(

)


@Composable
fun GithubTheme(darkTheme: Boolean = isSystemDarkTheme(),content: @Composable() -> Unit){
    val colors = if(darkTheme){
        DarkColorPalette
    }else{
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}