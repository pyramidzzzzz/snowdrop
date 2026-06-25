package site.remlit.snowdrop.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.painterResource
import snowdrop.shared.generated.resources.Res
import snowdrop.shared.generated.resources.icon_globe_20px
import snowdrop.shared.generated.resources.icon_home_20px
import snowdrop.shared.generated.resources.icon_lock_20px
import snowdrop.shared.generated.resources.icon_mail_20px

@Composable
fun Visibility(visibility: String) {
	when (visibility) {
		"public" -> Icon(painterResource(Res.drawable.icon_globe_20px) ,null)
		"unlisted" -> Icon(painterResource(Res.drawable.icon_home_20px) ,null)
		"private" -> Icon(painterResource(Res.drawable.icon_lock_20px) ,null)
		"direct" -> Icon(painterResource(Res.drawable.icon_mail_20px) ,null)
	}
}