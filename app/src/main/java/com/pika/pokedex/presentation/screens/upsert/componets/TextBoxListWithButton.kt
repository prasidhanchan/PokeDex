package com.pika.pokedex.presentation.screens.upsert.componets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.pika.pokedex.R
import com.pika.pokedex.presentation.components.PokeButton
import com.pika.pokedex.presentation.components.PokeTextBox
import com.pika.pokedex.presentation.screens.upsert.UiState

/**
 * Animated TextBox composable that displays list of [PokeTextBox] vertically
 * @param visible Visibility of the TextBoxes
 * @param uiState Requires [UiState]
 * @param onValueChangeName on value change lambda of the name triggered when name value is changed
 * @param onValueChangeDes on value change lambda of the description triggered when description value is changed
 * @param onValueChangeType on value change lambda of the type triggered when type value is changed
 * @param onValueChangeCategory on value change lambda of the category triggered when category value is changed
 * @param onValueChangeHeight on value change lambda of the height triggered when height value is changed
 * @param onValueChangeWeight on value change lambda of the weight triggered when weight value is changed
 */
@Composable
fun TextBoxListWithButton(
    visible: Boolean,
    uiState: UiState,
    onValueChangeName: (String) -> Unit,
    onValueChangeDes: (String) -> Unit,
    onValueChangeType: (String) -> Unit,
    onValueChangeCategory: (String) -> Unit,
    onValueChangeHeight: (String) -> Unit,
    onValueChangeWeight: (String) -> Unit,
    onButtonPressed: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(durationMillis = 350)
        ) + slideInVertically(
            animationSpec = tween(durationMillis = 350),
            initialOffsetY = { it }
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 350)
        ) + slideOutVertically(
            animationSpec = tween(durationMillis = 350),
            targetOffsetY = { it }
        )
    ) {
        Surface(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 30.dp, horizontal = 20.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PokeTextBox(
                    text = uiState.nameState,
                    onValueChange = onValueChangeName,
                    placeHolder = stringResource(R.string.name),
                    onFinish = { focusManager.moveFocus(FocusDirection.Next) }
                )

                PokeTextBox(
                    text = uiState.descriptionState,
                    onValueChange = onValueChangeDes,
                    placeHolder = stringResource(R.string.description),
                    singleLine = false,
                    onFinish = { focusManager.moveFocus(FocusDirection.Next) }
                )

                PokeTextBox(
                    text = uiState.typeState,
                    onValueChange = onValueChangeType,
                    placeHolder = stringResource(R.string.type),
                    onFinish = { focusManager.moveFocus(FocusDirection.Next) }
                )

                PokeTextBox(
                    text = uiState.categoryState,
                    onValueChange = onValueChangeCategory,
                    placeHolder = stringResource(id = R.string.category),
                    onFinish = { focusManager.moveFocus(FocusDirection.Next) }
                )

                PokeTextBox(
                    text = uiState.heightState,
                    onValueChange = onValueChangeHeight,
                    placeHolder = stringResource(id = R.string.height),
                    onFinish = { focusManager.moveFocus(FocusDirection.Next) }
                )

                PokeTextBox(
                    text = uiState.weightState,
                    onValueChange = onValueChangeWeight,
                    placeHolder = stringResource(id = R.string.weight),
                    imeAction = ImeAction.Done,
                    onFinish = {
                        focusManager.clearFocus()
                        onButtonPressed()
                    }
                )

                PokeButton(
                    text = if (uiState.idState != null)
                        stringResource(R.string.update_pokemon) else
                        stringResource(id = R.string.add_pokemon),
                    enabled = !uiState.loading,
                    onClick = onButtonPressed
                )
            }
        }
    }
}