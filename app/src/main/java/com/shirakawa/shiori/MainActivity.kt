package com.shirayuki.shiori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                    MultimediaConverterUI()
                }
            }
        }
    }
}

@Composable
fun MultimediaConverterUI() {
    var inputFilePath by remember { mutableStateOf(TextFieldValue("")) }
    var outputFilePath by remember { mutableStateOf(TextFieldValue("")) }
    var selectedFormat by remember { mutableStateOf("mp4") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input file picker
        BasicTextField(
            value = inputFilePath,
            onValueChange = { inputFilePath = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(8.dp)
                ) {
                    if (inputFilePath.text.isEmpty()) {
                        Text("Select input file")
                    }
                    innerTextField()
                }
            }
        )

        // Output file path
        BasicTextField(
            value = outputFilePath,
            onValueChange = { outputFilePath = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(8.dp)
                ) {
                    if (outputFilePath.text.isEmpty()) {
                        Text("Enter output file path")
                    }
                    innerTextField()
                }
            }
        )

        // Format selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Output Format: ")
            DropdownMenuExample(selectedFormat) { format ->
                selectedFormat = format
            }
        }

        // Convert button
        Button(
            onClick = {
                // Here, you would trigger the FFmpeg conversion
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Convert")
        }
    }
}

@Composable
fun DropdownMenuExample(selectedFormat: String, onFormatSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val formats = listOf("mp4", "mkv", "avi", "mp3", "wav")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Button(onClick = { expanded = true }) {
            Text(selectedFormat)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            formats.forEach { format ->
                DropdownMenuItem(onClick = {
                    onFormatSelected(format)
                    expanded = false
                }) {
                    Text(text = format)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeApplicationTheme {
        MultimediaConverterUI()
    }
}