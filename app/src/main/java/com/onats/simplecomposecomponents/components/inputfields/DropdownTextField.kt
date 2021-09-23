
//reference: https://stackoverflow.com/questions/67111020/exposed-drop-down-menu-for-jetpack-compose

package com.onats.simplecomposecomponents.components.inputfields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.onats.simplecomposecomponents.util.testList


@Composable
fun DropDownTextField(
    items: List<String>,
    label: String,
    onItemSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    ) {
        OutlinedTextField(value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            modifier = Modifier
                .clickable { expanded = !expanded }
                .height(60.dp)
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(label) },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary
            ),
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            })
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = label
                        onItemSelected(label)
                        expanded = !expanded
                    }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewDropDownTextField() {
    DropDownTextField(items = testList, label = "Select Animal") { selectedItem ->

    }
}


