package com.pika.pokedex.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pika.pokedex.R

val mPlus = FontFamily(
    Font(R.font.mplus_bold, FontWeight.Bold),
    Font(R.font.mplus_semi_bold, FontWeight.SemiBold),
    Font(R.font.mplus_medium, FontWeight.Medium),
    Font(R.font.mplus_regular, FontWeight.Normal),
    Font(R.font.mplus_light, FontWeight.Light),
    Font(R.font.mplus_extra_light, FontWeight.ExtraLight),
    Font(R.font.mplus_thin, FontWeight.Thin)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)