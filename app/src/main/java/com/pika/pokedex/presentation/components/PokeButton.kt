package com.pika.pokedex.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pika.pokedex.domain.util.Constants.blue

/**
 * PokeButton composable created from [TextButton]
 * @param modifier Requires a [Modifier]
 * @param text Requires the button Text
 * @param enabled When false Button will be disabled
 * @param onClick The on Click lambda of triggered on click of the button
 */
@Composable
fun PokeButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    color: Color = blue,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        enabled = enabled
    ) {
        if (enabled) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier.size(30.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        }
    }
}

@Preview
@Composable
private fun PokeButtonPreview() {
    PokeButton(
        text = "Add Pokemon",
        onClick = { }
    )
}