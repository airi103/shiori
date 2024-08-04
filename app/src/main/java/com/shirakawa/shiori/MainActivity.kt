package com.shirayuki.shiori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.border  // Add this line
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shirayuki.shiori.ui.theme.MyComposeApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConverterUI()
                }
            }
        }
    }
}

@Composable
fun ConverterUI() {
    var inputFilePath by remember { mutableStateOf(TextFieldValue("")) }
    var outputFilePath by remember { mutableStateOf(TextFieldValue("")) }
    var selectedFormat by remember { mutableStateOf("Select Format") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("FFmpeg Multimedia Converter", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        BasicTextField(
            value = inputFilePath,
            onValueChange = { inputFilePath = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Gray))
                .padding(10.dp),
            decorationBox = { innerTextField ->
                if (inputFilePath.text.isEmpty()) {
                    Text("Input File Path", color = Color.Gray)
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        BasicTextField(
            value = outputFilePath,
            onValueChange = { outputFilePath = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Gray))
                .padding(10.dp),
            decorationBox = { innerTextField ->
                if (outputFilePath.text.isEmpty()) {
                    Text("Output File Path", color = Color.Gray)
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = selectedFormat,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* Open format selection dialog */ }
                .border(BorderStroke(1.dp, Color.Gray))
                .padding(10.dp),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { /* Start conversion */ }) {
            Text("Convert")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConverterUIPreview() {
    MyComposeApplicationTheme {
        ConverterUI()
    }
}
