import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.overrun.compose.progressBar

@Composable
@Preview
fun App() {
    val text = remember { mutableStateOf("Hello, World!") }
    val progress = remember { mutableStateOf(0f) }

    MaterialTheme {
        Button(onClick = {
            text.value = "Hello, Desktop!"
        }) {
            Text(text.value)
        }
        Column(modifier = Modifier.padding(
            top = 100.dp
        )) {
            progressBar(
                modifier = Modifier,
                progress = progress.value,
                color = Color.Green
            )
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()

    }
}
