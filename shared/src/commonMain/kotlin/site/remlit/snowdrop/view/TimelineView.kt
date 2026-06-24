package site.remlit.snowdrop.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavOptions
import org.jetbrains.compose.resources.painterResource
import site.remlit.snowdrop.Settings
import site.remlit.snowdrop.api.timeline.getHomeTimeline
import site.remlit.snowdrop.component.Status
import site.remlit.snowdrop.component.ViewSurface
import site.remlit.snowdrop.model.Status
import site.remlit.snowdrop.util.LocalNavController
import snowdrop.shared.generated.resources.Res
import snowdrop.shared.generated.resources.icon_settings_24px

@Composable
@Preview
fun TimelineView() = ViewSurface {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		val navHandler = LocalNavController.current

		var timeline = remember { mutableStateListOf<Status>() }

		LaunchedEffect(Unit) {
			val res = getHomeTimeline()
			if (res.error) return@LaunchedEffect
			if (res.response == null) return@LaunchedEffect
			timeline.addAll(res.response)
		}

		val scrollState = rememberScrollState()

		TopAppBar(
			title = {
				Text("Timeline")
			},
			actions = {
				IconButton(onClick = { navHandler.navigate(Settings) }) {
					Icon(painterResource(Res.drawable.icon_settings_24px), null)
				}
			}
		)

		Column(
			modifier = Modifier
				.verticalScroll(scrollState)
				.fillMaxSize()
		) {
			for (status in timeline) {
				Status(status)
			}
		}
	}
}