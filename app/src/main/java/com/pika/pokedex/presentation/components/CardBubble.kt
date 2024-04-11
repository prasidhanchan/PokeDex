package com.pika.pokedex.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pika.pokedex.presentation.ui.theme.mPlus

/**
 * card Bubble composable to display details suc as pokemon type, category, etc
 * @param text The text to be displayed
 */
@Composable
fun CardBubble(
    text: String,
    fontSize: Int = 14
) {
    Surface(
        modifier = Modifier
            .wrapContentHeight(Alignment.CenterVertically)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(all = 2.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.White.copy(alpha = 0.3f)
    ) {
        Box(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = fontSize.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = mPlus,
                    color = Color.White
                )
            )
        }
    }
}