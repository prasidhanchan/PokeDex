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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pika.pokedex.domain.util.Constants.blue
import com.pika.pokedex.domain.util.Constants.darkGray
import com.pika.pokedex.domain.util.Constants.green
import com.pika.pokedex.domain.util.Constants.peach
import com.pika.pokedex.domain.util.Constants.red
import com.pika.pokedex.domain.util.Constants.yellow
import com.pika.pokedex.presentation.screens.upsert.componets.TextBoxListWithButton
import com.pika.pokedex.presentation.screens.upsert.componets.ColorPalette
import com.pika.pokedex.presentation.screens.upsert.componets.ImagePlaceHolder
import com.pika.pokedex.presentation.screens.upsert.componets.UpsertTopBar
import kotlinx.coroutines.delay

@Composable
fun UpsertScreen(
    visible: Boolean = false,
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
        blue,
        yellow,
        red,
        green,
        peach,
        darkGray,
    )

    val selectedColor = uiState.colorState.substring(2).toLong(16)

    var isVisible by remember { mutableStateOf(visible) }

    LaunchedEffect(key1 = Unit) {
        delay(200L)
        isVisible = true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(selectedColor)
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
                    visible = isVisible,
                    image = uiState.imageState,
                    onClick = {
                        galleryLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                )

                ColorPalette(
                    visible = isVisible,
                    colorList = colorList,
                    selectedColor = Color(selectedColor),
                    onSelectColor = { color ->
                        onValueChangeColor(color)
                    }
                )
            }

            TextBoxListWithButton(
                visible = isVisible,
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
private fun UpsertScreenPreview() {
    UpsertScreen(
        visible = true,
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