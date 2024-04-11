package com.pika.pokedex.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pika.pokedex.presentation.ui.theme.mPlus

/**
 * Custom TextField composable for PokeDex
 * @param modifier requires [Modifier]
 * @param text Requires the actual text typed in the TextField
 * @param onValueChange on value change of the TextField
 * @param keyboardType Requires Keyboard type i.e Text by default
 * @param imeAction Requires the ImeAction that is Next by default
 * @param singleLine When true, the TextBox becomes a single horizontally scrolled TextField
 * @param enableFocusRequester FocusRequester boolean to enable Focus Request
 * @param onFinish The on finish lambda triggered when the keyboard button Done, Search, Next, etc is pressed
 */
@Composable
fun PokeTextBox(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    singleLine: Boolean = true,
    enableFocusRequester: Boolean = false,
    onFinish: (KeyboardActionScope.() -> Unit)
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = text,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = when (imeAction) {
            ImeAction.Next -> KeyboardActions(onNext = onFinish)
            ImeAction.Done -> KeyboardActions(onDone = onFinish)
            ImeAction.Search -> KeyboardActions(onSearch = onFinish)
            else -> KeyboardActions(onGo = onFinish)
        },
        placeholder = {
            Text(
                text = placeHolder,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = mPlus
                )
            )
        },
        singleLine = singleLine,
        textStyle = TextStyle(fontFamily = mPlus),
        shape = RoundedCornerShape(15.dp)
    )

    if (enableFocusRequester) {
        LaunchedEffect(key1 = Unit) {
            focusRequester.requestFocus()
        }
    }
}