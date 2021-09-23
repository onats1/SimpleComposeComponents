package com.onats.simplecomposecomponents.components.inputfields

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent

@Composable
private fun OtpTextBox(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    onNextClick: () -> Unit,
    onValueChange: (String) -> Unit,
) {

    var character by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = character,
        onValueChange = { characterInput ->
            character = if (characterInput.isNotEmpty()) {
                //Take only the latest character
                val relevantChar =
                    characterInput.substring(characterInput.length - 1, characterInput.length)
                relevantChar
            } else {
                characterInput
            }
            onValueChange(character)
        },
        shape = RoundedCornerShape(6.dp),
        maxLines = 1,
        modifier = modifier
            .focusRequester(focusRequester)
            .width(45.dp)
            .height(60.dp),
        colors = outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { onNextClick() },

            )
    )

}

@ExperimentalComposeUiApi
@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    length: Int = 6,
    onOtpComplete: (String) -> Unit // Currently set to be called when the input is on the last field. You can edit it to be called in real time.
) {

    val otpCharacters by rememberSaveable {
        mutableStateOf(mutableMapOf<Int, String>()) // Using a map to be able to edit fields dynamically.
    }

    val focusRequesters: List<FocusRequester> = rememberSaveable {
        val temp = mutableListOf<FocusRequester>()
        repeat(length) {
            temp.add(FocusRequester())
        }
        temp
    }

    Row(
        modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        (0 until length).forEach { index ->
            Spacer(modifier = Modifier.width(6.dp))
            OtpTextBox(
                modifier = Modifier.onKeyEvent { event ->
                    if (event.key == Key.Backspace) {
                        if (index > 0)
                            focusRequesters[index - 1].requestFocus()
                        true
                    } else {
                        false
                    }
                },
                focusRequester = focusRequesters[index],
                onNextClick = {
                    if (index == length - 1) {
                        var otpValue = ""
                        otpCharacters.values.forEach { char ->
                            otpValue += char
                        }
                        onOtpComplete(otpValue)
                    } else {
                        focusRequesters[index + 1].requestFocus()
                    }
                }
            ) {
                otpCharacters[index] = it
                if (index < length - 1 && it.isNotEmpty()) {
                    focusRequesters[index + 1].requestFocus()
                }
            }
            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun OtpBoxPreview() {

}



















