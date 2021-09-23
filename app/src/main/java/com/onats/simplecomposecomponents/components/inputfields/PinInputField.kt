package com.onats.simplecomposecomponents.components.inputfields

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
private fun PinCircle(
    isHighlighted: Boolean
) {
    Surface(
        modifier = Modifier
            .height(12.dp)
            .width(12.dp),
        shape = RoundedCornerShape(6.dp),
        color = if (isHighlighted) Color.Black else Color.Gray
    ) {}
}

@ExperimentalComposeUiApi
@Composable
fun PinField(
    onPinComplete: (String) -> Unit
) {

    var pinInputText by rememberSaveable { mutableStateOf("") }
    val focusRequester = FocusRequester()
    var highlightedTrack by remember {
        mutableStateOf(-1)
    }
    LaunchedEffect(true) {
        focusRequester.requestFocus()
    }

    Row {
        for (position in 0 until 6) {
            Spacer(modifier = Modifier.width(6.dp))
            PinCircle(
                isHighlighted = position <= highlightedTrack
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        TextField(
            value = "",
            modifier = Modifier
                .size(0.dp)
                .focusRequester(focusRequester)
                .onKeyEvent { event ->
                    if (event.key == Key.Backspace) {
                        pinInputText = ""
                        highlightedTrack = -1
                        true
                    } else {
                        false
                    }
                },
            onValueChange = { pin ->
                if (pinInputText.length <= 6) {
                    pinInputText += pin
                    highlightedTrack++
                }
                if (pinInputText.length == 6) {
                    onPinComplete(pinInputText)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }


}

