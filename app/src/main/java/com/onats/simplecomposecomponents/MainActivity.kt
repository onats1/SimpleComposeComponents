package com.onats.simplecomposecomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onats.simplecomposecomponents.ui.ComponentScreen
import com.onats.simplecomposecomponents.ui.PinInputScreen
import com.onats.simplecomposecomponents.ui.theme.SimpleComposeComponentsTheme

// Sample Usage
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SimpleComposeComponentsTheme {
                // A surface container using the 'background' color from the theme
                NavHost(navController = navController, startDestination = "componentScreen") {
                    composable("pinInputScreen") { PinInputScreen() }
                    composable("componentScreen") { ComponentScreen(navController) }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleComposeComponentsTheme {
        Greeting("Android")
    }
}