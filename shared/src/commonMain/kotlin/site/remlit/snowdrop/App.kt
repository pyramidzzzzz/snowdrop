package site.remlit.snowdrop

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.russhwolf.settings.ExperimentalSettingsApi
import io.kamel.image.config.LocalKamelConfig
import site.remlit.snowdrop.util.kamelConfig
import site.remlit.snowdrop.util.settings
import site.remlit.snowdrop.util.settingsContext

@Composable
@Preview
@OptIn(ExperimentalSettingsApi::class)
fun App() {
	CompositionLocalProvider(LocalKamelConfig provides kamelConfig) {

		val loggedIn by settings.getBooleanFlow("logged_in", false)
			.collectAsState(false, settingsContext)

		if (!loggedIn) Login()
		else LoggedIn()
	}
}