package com.pika.pokedex.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
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
 * @param onClick The on CLick lambda of triggered on click of the button
 */
@Composable
fun PokeButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
            .height(60.dp),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = blue
        ),
        enabled = enabled
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        )
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