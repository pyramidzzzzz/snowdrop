package site.remlit.snowdrop.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import site.remlit.snowdrop.model.Status

@Composable
fun Status(status: Status) {
	Text("$status")
}