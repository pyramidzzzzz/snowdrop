package site.remlit.snowdrop.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import site.remlit.snowdrop.model.User

@Composable
fun Avatar(user: User) {

	@Composable
	fun fallback() {
		Box(
			modifier = Modifier.clip(RoundedCornerShape(15.dp))
				.background(MaterialTheme.colorScheme.surfaceContainerHigh)
				.height(48.dp)
				.width(48.dp)
		)
	}

	if (user.avatar != null) {
		KamelImage(
			{ asyncPainterResource(user.avatarStatic!!) },
			"Profile",
			onLoading = { fallback() },
			modifier = Modifier.clip(RoundedCornerShape(15.dp))
				.height(48.dp)
				.width(48.dp)
		)
	} else fallback()
}