package site.remlit.snowdrop.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import be.digitalia.compose.htmlconverter.htmlToAnnotatedString
import site.remlit.snowdrop.model.Status

@Composable
fun HtmlContent(
	string: String,
	mentions: List<Status.Mention> = emptyList()
) {
	val uriHandler = LocalUriHandler.current

	val linkListener = LinkInteractionListener { link ->
		if (link is LinkAnnotation.Url) {
			val mention = mentions.firstOrNull { m -> m.url == link.url }

			if (mention != null) TODO()
			else uriHandler.openUri(link.url)
		}
	}

	Text(text = remember(string) {
		htmlToAnnotatedString(string, linkInteractionListener = linkListener)
	})
}