package com.pika.pokedex.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pika.pokedex.R
import com.pika.pokedex.domain.util.Constants.gray
import com.pika.pokedex.presentation.ui.theme.mPlus

/**
 * PokeDex Dialog composable that displays a Alert Dialog
 * @param modifier Requires [Modifier]
 * @param visible Visibility of the Dialog
 * @param title Title o the Dialog
 * @param subTitle Subtitle of the dialog
 * @param onConfirm on Confirm lambda triggered when confirm button id pressed
 * @param onDismiss on Dismiss lambda triggered when dismiss button id pressed
 */
@Composable
fun PokeDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    title: String,
    subTitle: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(durationMillis = 350)
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 350)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f))
                .clickable(
                    enabled = false,
                    onClick = onDismiss
                ),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = modifier
                    .padding(all = 20.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .fillMaxWidth(0.85f),
                shape = RoundedCornerShape(30.dp),
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 20.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = mPlus
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = subTitle,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = mPlus
                        )
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        PokeButton(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(2f),
                            text = stringResource(R.string.confirm),
                            onClick = onConfirm
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        PokeButton(
                            modifier = Modifier
                                .height(50.dp)
                                .weight(2f),
                            text = stringResource(R.string.cancel),
                            color = gray,
                            onClick = onDismiss
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PokeDexDialogPreview() {
    PokeDialog(
        visible = true,
        title = "Delete Pokemon?",
        subTitle = "Are you sure you want to delete this pokemon?",
        onConfirm = { },
        onDismiss = { }
    )
}