package com.pika.pokedex.presentation.screens.upsert

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pika.pokedex.domain.models.Pokemon
import com.pika.pokedex.presentation.screens.upsert.componets.TextBoxListWithButton
import com.pika.pokedex.presentation.screens.upsert.componets.ColorPalette
import com.pika.pokedex.presentation.screens.upsert.componets.ImagePlaceHolder
import com.pika.pokedex.presentation.screens.upsert.componets.UpsertTopBar

@Composable
fun UpsertScreen(
    visible: Boolean,
    pokemon: Pokemon?,
    uiState: UiState,
    onValueChangeName: (String) -> Unit,
    onValueChangeDes: (String) -> Unit,
    onValueChangeType: (String) -> Unit,
    onValueChangeCategory: (String) -> Unit,
    onValueChangeHeight: (String) -> Unit,
    onValueChangeWeight: (String) -> Unit,
    onValueChangeColor: (String) -> Unit,
    onValueChangeImage: (String) -> Unit,
    onButtonPressed: () -> Unit,
    onBackPressed: () -> Unit
) {
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            onValueChangeImage(uri.toString())
        }
    }

    val colorList = listOf(
        Color(0XFF00AFFF),
        Color(0xFFFAD04D),
        Color(0xFFF46565),
        Color(0xFF48BA78),
        Color(0xFFF08181),
        Color(0XFF7A6D6D),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(uiState.colorState.substring(2).toLong(16))
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(all = 30.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UpsertTopBar(
                    title = uiState.nameState,
                    onBackPressed = onBackPressed
                )

                ImagePlaceHolder(
                    visible = visible,
                    image = uiState.imageState,
                    onClick = {
                        galleryLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                )

                ColorPalette(
                    visible = visible,
                    colorList = colorList,
                    selectedColor = Color(uiState.colorState.substring(2).toLong(16)),
                    onSelectColor = { color ->
                        onValueChangeColor(color)
                    }
                )
            }

            TextBoxListWithButton(
                visible = visible,
                pokemon = pokemon,
                uiState = uiState,
                onValueChangeName = onValueChangeName,
                onValueChangeDes = onValueChangeDes,
                onValueChangeType = onValueChangeType,
                onValueChangeCategory = onValueChangeCategory,
                onValueChangeHeight = onValueChangeHeight,
                onValueChangeWeight = onValueChangeWeight,
                onButtonPressed = onButtonPressed
            )
        }
    }
}

@Preview
@Composable
private fun UpsertScreenContentPreview() {
    UpsertScreen(
        visible = true,
        pokemon = null,
        uiState = UiState(),
        onValueChangeName = { },
        onValueChangeDes = { },
        onValueChangeType = { },
        onValueChangeCategory = { },
        onValueChangeHeight = { },
        onValueChangeWeight = { },
        onValueChangeColor = { },
        onValueChangeImage = { },
        onButtonPressed = { },
        onBackPressed = { }
    )
}