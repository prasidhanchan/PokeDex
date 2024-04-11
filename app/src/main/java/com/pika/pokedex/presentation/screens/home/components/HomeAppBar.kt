package com.pika.pokedex.presentation.screens.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pika.pokedex.R
import com.pika.pokedex.presentation.components.PokeTextBox
import com.pika.pokedex.presentation.screens.home.UiState
import com.pika.pokedex.presentation.ui.theme.mPlus

@Composable
fun HomeAppBar(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onSearchValueChange: (String) -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mPlus,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            if (!visible) {
                Icon(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = { visible = true }
                    ),
                    painter = painterResource(id = R.drawable.search),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = stringResource(R.string.search)
                )
            }
        }

        if (visible) {
            PokeTextBox(
                enableFocusRequester = true,
                text = uiState.searchState,
                onValueChange = onSearchValueChange,
                placeHolder = stringResource(R.string.pikachu),
                imeAction = ImeAction.Search,
                onFinish = {
                    onSearchValueChange(uiState.searchState)
                    visible = false
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun HomeAppBarPreview() {
    HomeAppBar(
        uiState = UiState(),
        onSearchValueChange = { }
    )
}