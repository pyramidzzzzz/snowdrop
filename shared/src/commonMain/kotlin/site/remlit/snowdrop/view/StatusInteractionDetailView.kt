package site.remlit.snowdrop.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import site.remlit.snowdrop.api.statuses.getStatusFavouritedBy
import site.remlit.snowdrop.api.statuses.getStatusRebloggedBy
import site.remlit.snowdrop.component.Avatar
import site.remlit.snowdrop.component.ViewSurface
import site.remlit.snowdrop.model.Account
import site.remlit.snowdrop.model.ApiResponse
import site.remlit.snowdrop.util.LocalNavController
import site.remlit.snowdrop.util.SnackbarController
import site.remlit.snowdrop.util.bgIO
import snowdrop.shared.generated.resources.Res
import snowdrop.shared.generated.resources.icon_arrow_back_24

enum class InteractionViewType {
	Boost, Like, Reaction
}

@Composable
fun StatusInteractionDetailView(
	id: String,
	type: InteractionViewType
) = ViewSurface {
	val navHandler = LocalNavController.current
	val snackbarHandler = SnackbarController.current

	TopAppBar(
		navigationIcon = {
			IconButton(onClick = { navHandler.popBackStack() }) {
				Icon(painterResource(Res.drawable.icon_arrow_back_24), null)
			}
		},
		title = {
			when (type) {
				InteractionViewType.Boost -> Text("Boosted by")
				InteractionViewType.Like -> Text("Liked by")
				InteractionViewType.Reaction -> Text("Reacted by")
			}
		}
	)

	when (type) {
		InteractionViewType.Like, InteractionViewType.Boost -> {
			val accounts = remember { mutableStateListOf<Account>() }
			bgIO {
				val res: ApiResponse<List<Account>> = when (type) {
					InteractionViewType.Like -> getStatusFavouritedBy(id)
					InteractionViewType.Boost -> getStatusRebloggedBy(id)
				}
				if (res.error || res.response == null) {
					res.handleError(snackbarController = snackbarHandler)
					return@bgIO
				}
				accounts.addAll(res.response)
			}

			// todo: scrolling
			LazyColumn {
				accounts.forEach { account ->
					item {
						Row(
							horizontalArrangement = Arrangement.spacedBy(10.dp)
						) {
							Avatar(account)

							Column {
								Text(account.displayName ?: account.username)
								Text("@${account.acct}")
							}
						}
						HorizontalDivider(
							thickness = 1.dp,
							color = MaterialTheme.colorScheme.surfaceContainer
						)
					}
				}
			}
		}
		// todo: implement this!
		InteractionViewType.Reaction -> {
			var currentTab by remember { mutableStateOf(0) }

			PrimaryScrollableTabRow(currentTab) {

			}
			LazyColumn {

			}
		}
	}
}
