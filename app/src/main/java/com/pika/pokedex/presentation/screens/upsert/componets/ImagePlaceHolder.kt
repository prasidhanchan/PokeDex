package com.pika.pokedex.presentation.screens.upsert.componets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pika.pokedex.R

/**
 * ImagePlaceHolder composable for displaying the selected Pokemon image from gallery
 * @param modifier Requires a [Modifier]
 * @param image Requires the selected Image
 * @param onClick The on click lambda triggered when the ImagePlaceHolder is clicked
 */
@Composable
fun ImagePlaceHolder(
    modifier: Modifier = Modifier,
    visible: Boolean,
    image: String?,
    onClick: () -> Unit
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
        Surface(
            modifier = modifier
                .size(150.dp)
                .clip(CircleShape)
                .clickable(onClick = onClick),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (image != null) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = image,
                        contentScale = ContentScale.Crop,
                        contentDescription = stringResource(R.string.selected_image)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.add_pokemon),
                        contentDescription = stringResource(id = R.string.add_pokemon)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ImagePlaceHolderPreview() {
    ImagePlaceHolder(
        visible = true,
        image = null,
        onClick = { }
    )
}