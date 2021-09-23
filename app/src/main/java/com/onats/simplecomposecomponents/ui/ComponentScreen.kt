package com.onats.simplecomposecomponents.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.onats.simplecomposecomponents.components.inputfields.DropDownTextField
import com.onats.simplecomposecomponents.components.inputfields.OtpTextField
import com.onats.simplecomposecomponents.components.buttons.FilledButton
import com.onats.simplecomposecomponents.util.testList

@ExperimentalComposeUiApi
@Composable
fun ComponentScreen(
    navController: NavController
) {
    Scaffold {

        var otpText by rememberSaveable { mutableStateOf("") }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            DropDownTextField(
                items = testList,
                label = "Select animal"
            ) { selectedAnimal ->

            }
            Spacer(modifier = Modifier.height(50.dp))
            OtpTextField { otpInput ->
                otpText = otpInput
            }
            Text(text = otpText)
            Spacer(modifier = Modifier.height(50.dp))
            FilledButton(text = "Click to enter pin") {
                navController.navigate("pinInputScreen")
            }
        }
    }
}