package site.remlit.snowdrop.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reaction(
	val id: String,
	val name: String,
	val url: String? = null,
	@SerialName("static_url")
	val staticUrl: String? = null,
	val account: Account
)
