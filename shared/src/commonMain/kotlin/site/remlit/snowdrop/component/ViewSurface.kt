package site.remlit.snowdrop.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ViewSurface(content: @Composable () -> Unit) {
	Column(
		modifier = Modifier.background(MaterialTheme.colorScheme.background)
			.fillMaxWidth().fillMaxHeight()
	) {
		content()
	}
}
