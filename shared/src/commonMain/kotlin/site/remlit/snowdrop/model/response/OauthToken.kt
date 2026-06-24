package site.remlit.snowdrop.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OauthToken(
	@SerialName("access_token")
	val accessToken: String
)
