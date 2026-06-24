package site.remlit.snowdrop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import site.remlit.snowdrop.view.Notifications
import site.remlit.snowdrop.view.Timeline
import snowdrop.shared.generated.resources.Res
import snowdrop.shared.generated.resources.icon_home_24px
import snowdrop.shared.generated.resources.icon_notifications_24px

@Composable
fun LoggedIn() {
	var selection by remember { mutableStateOf(0) }

	MaterialTheme {

		Scaffold(
			bottomBar = {
				NavigationBar {
					NavigationBarItem(
						selected = (selection == 0),
						onClick = { selection = 0 },
						icon = { Icon(
							painterResource(Res.drawable.icon_home_24px),
							"Timeline"
						) },
						label = { Text("Timeline") }
					)

					NavigationBarItem(
						selected = (selection == 1),
						onClick = { selection = 1 },
						icon = { Icon(
							painterResource(Res.drawable.icon_notifications_24px),
							"Notifications"
						) },
						label = { Text("Notifications") }
					)
				}
			}
		) {
			Column {
				TopAppBar(
					title = {
						when (selection) {
							0 -> Text("Timeline")
							1 -> Text("Notifications")

							else -> Text("Error")
						}
					}
				)

				Column(
				) {
					when (selection) {
						0 -> Timeline()
						1 -> Notifications()

						else -> Text("Something went wrong...")
					}
				}
			}
		}

	}
}