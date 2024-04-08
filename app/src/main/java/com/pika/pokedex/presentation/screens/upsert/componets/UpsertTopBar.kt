package com.pika.pokedex.presentation.screens.upsert.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pika.pokedex.R

/**
 * Upsert screen TopBar composable
 * @param title The Pokemon name to be displayed in the TopBar as the text is typed in the TextField
 * @param onBackPressed The on Back press lambda triggered when back button is pressed
 */
@Composable
fun UpsertTopBar(
    title: String,
    onBackPressed: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = onBackPressed
            ),
            painter = painterResource(id = R.drawable.back),
            tint = Color.White,
            contentDescription = stringResource(id = R.string.back)
        )

        Text(
            modifier = Modifier.weight(6f),
            text = title,
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0XFFFFFFFF
)
@Composable
private fun UpsertTopBarPreview() {
    UpsertTopBar(
        title = "Pikachu",
        onBackPressed = { }
    )
}