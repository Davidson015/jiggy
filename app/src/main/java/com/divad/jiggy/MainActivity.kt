package com.divad.jiggy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.divad.jiggy.ui.theme.JiggyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JiggyTheme {
                var textFieldValue by remember {
                    mutableStateOf("")
                }
                var values by remember {
                    mutableStateOf(listOf<String>())
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = textFieldValue,
                            onValueChange = { text ->
                                textFieldValue = text
                            },
                            modifier = Modifier
                                .weight(1f),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if(textFieldValue.isNotBlank()) {
                                        values = values + textFieldValue
                                        textFieldValue = ""
                                    }
                                }
                            ),
                            singleLine = true,
                        )
                        
                        Spacer(modifier = Modifier.width(15.dp))

                        Button(
                            onClick = {
                                if(textFieldValue.isNotBlank()) {
                                    values = values + textFieldValue
                                    textFieldValue = ""
                                }
                            },
                            modifier = Modifier
                                .fillMaxHeight(.068f)
                        ) {
                            Text(text = "Add Value!")
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(30.dp))

                    ValueList(values = values)
                }
            }
        }
    }
}

@Composable
fun ValueList(
    values: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(values) { currentValue ->
            Text(
                text = currentValue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
            Divider(thickness = 3.dp)
        }
    }
}