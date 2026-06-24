package site.remlit.snowdrop

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.russhwolf.settings.ExperimentalSettingsApi
import site.remlit.snowdrop.util.settings
import site.remlit.snowdrop.util.settingsContext

@Composable
@Preview
@OptIn(ExperimentalSettingsApi::class)
fun App() {
	val loggedIn by settings.getBooleanFlow("logged_in", false)
		.collectAsState(false, settingsContext)

	LaunchedEffect(Unit) {}

	if (!loggedIn) Login()
	else LoggedIn()
}