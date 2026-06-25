package site.remlit.snowdrop.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import site.remlit.snowdrop.ProfileRoute
import site.remlit.snowdrop.model.Notification
import site.remlit.snowdrop.util.LocalNavController
import site.remlit.snowdrop.util.toRelativeString
import snowdrop.shared.generated.resources.Res
import snowdrop.shared.generated.resources.icon_add_24px
import snowdrop.shared.generated.resources.icon_edit_24px
import snowdrop.shared.generated.resources.icon_repeat_24px
import snowdrop.shared.generated.resources.icon_poll_24px
import snowdrop.shared.generated.resources.icon_star_24px
import snowdrop.shared.generated.resources.icon_tooth_24px

@Composable
fun Notification(notification: Notification) {
	val navHandler = LocalNavController.current

	if (notification.type == "mention" && notification.status != null) {
		Status(notification.status)
	} else {
		Column {
			Column(
				modifier = Modifier.padding(15.dp)
			) {
				Row(
					horizontalArrangement = Arrangement.spacedBy(10.dp)
				) {
					when (notification.type) {
						"favourite" -> Icon(
							painterResource(Res.drawable.icon_star_24px), null,
							tint = MaterialTheme.colorScheme.primary
						)
						"pleroma:emoji_reaction" -> Icon(
							painterResource(Res.drawable.icon_add_24px), null,
							tint = MaterialTheme.colorScheme.primary
						)
						"reblog" -> Icon(
							painterResource(Res.drawable.icon_repeat_24px), null,
							tint = MaterialTheme.colorScheme.primary
						)
						"update" -> Icon(
							painterResource(Res.drawable.icon_edit_24px), null,
							tint = MaterialTheme.colorScheme.primary
						)
						"poll" -> Icon(
							painterResource(Res.drawable.icon_poll_24px), null,
							tint = MaterialTheme.colorScheme.primary
						)
						"bite" -> Icon( // todo: tooth, and test bite notifs
							painterResource(Res.drawable.icon_tooth_24px), null,
							tint = MaterialTheme.colorScheme.primary
						)
					}

					Row(
						modifier = Modifier
							.clickable(onClick = {
								navHandler.navigate(ProfileRoute(notification.account.id))
							})
					) {
						Avatar(notification.account, small = true)
					}

					Row(
						horizontalArrangement = Arrangement.spacedBy(5.dp)
					) {
						when (notification.type) {
							"favourite", "pleroma:emoji_reaction", "reblog", "update", "poll", "bite" -> Text(
								notification.account.displayName ?: notification.account.username,
								fontWeight = FontWeight.Bold,
								overflow = TextOverflow.Ellipsis,
								maxLines = 1,
								modifier = Modifier.clickable(
									onClick = { navHandler.navigate(ProfileRoute(notification.account.id))}
								)
							)
						}

						when (notification.type) {
							"favourite" -> Text(
								"liked your post",
								maxLines = 1,
								modifier = Modifier.weight(1f)
							)
							"pleroma:emoji_reaction" -> Text(
								"reacted with " + notification.emoji,
								maxLines = 1,
								modifier = Modifier.weight(1f)
							)
							"reblog" -> Text(
								"boosted your post",
								maxLines = 1,
								modifier = Modifier.weight(1f)
							)
							"update" -> Text(
								"edited a post",
								maxLines = 1,
								modifier = Modifier.weight(1f)
							)
							"poll" -> Text(
								"poll has ended",
								maxLines = 1,
								modifier = Modifier.weight(1f)
							)
							"bite" -> {
								if (notification.bite?.biteBack == true) Text(
									"bit you back",
									maxLines = 1,
									modifier = Modifier.weight(1f)
								)
								else if (notification.status != null) Text(
									"bit your post",
									maxLines = 1,
									modifier = Modifier.weight(1f)
								)
								else Text(
									"bit you",
									maxLines = 1,
									modifier = Modifier.weight(1f)
								)
							}
						}

						Text(
							"${notification.getCreatedAtTimestamp()?.toRelativeString()}",
							fontSize = 13.sp,
							maxLines = 1,
							modifier = Modifier.requiredWidth(IntrinsicSize.Min)
						)
					}




					/*Row(modifier = Modifier.width(IntrinsicSize.Min)) {

					}*/
				}

				if (notification.status != null) {
					Column(
						modifier = Modifier.padding(top = 10.dp)
					) {
						MiniStatus(notification.status)
					}
				}
			}

			HorizontalDivider(
				thickness = 1.dp,
				color = MaterialTheme.colorScheme.surfaceContainer
			)
		}
	}
}
