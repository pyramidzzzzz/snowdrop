package site.remlit.snowdrop.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Emoji(
	val shortcode: String,
	@SerialName("static_url")
	val staticUrl: String,
	val url: String,
	@SerialName("visible_in_picker")
	val visibleInPicker: Boolean,
	val category: String
)