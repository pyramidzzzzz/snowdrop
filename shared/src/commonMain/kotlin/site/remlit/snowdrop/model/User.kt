package site.remlit.snowdrop.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
	val id: String,
	val username: String,
	val acct: String,
	val fqn: String,
	@SerialName("display_name")
	val displayName: String,

	@SerialName("followers_count")
	val followersCount: Long,
	@SerialName("following_count")
	val followingCount: Long,
	@SerialName("statuses_count")
	val statusesCount: Long,

	val note: String,

	val url: String,
	val uri: String,

	val avatar: String,
	@SerialName("avatar_static")
	val avatarStatic: String,
	@SerialName("avatar_description")
	val avatarDescription: String,

	val header: String,
	@SerialName("header_static")
	val headerStatic: String,
	@SerialName("header_description")
	val headerDescription: String,

	// todo: why is this a string? what is this?
	val moved: String,

	val locked: Boolean,
	val bot: Boolean,
	@SerialName("is_cat")
	val isCat: Boolean = false,
	@SerialName("speak_as_cat")
	val speakAsCat: Boolean = false,
	val discoverable: Boolean,

	val fields: List<Field>,
	val source: Source? = null,
	val emojis: List<Emoji>,

	@SerialName("attribution_domains")
	val attributionDomains: List<String> = emptyList(),
	@SerialName("formatted_note")
	val formattedNote: String? = null,
	@SerialName("formatted_fields")
	val formattedFields: List<Field> = emptyList(),

	@SerialName("created_at")
	val createdAt: String,
	@SerialName("last_status_at")
	val lastStatusAt: String,
) {
	@Serializable
	data class Field(
		val name: String,
		val `value`: String,
		@SerialName("verified_at")
		val verifiedAt: String
	)

	@Serializable
	data class Source(
		val lang: String,
		val note: String,
		val privacy: String,
		val sensitive: Boolean,
		val fields: List<Field>,
		@SerialName("attribution_domains")
		val attributionDomains: List<String>,
		@SerialName("follow_requests_count")
		val followRequestsCount: Int
	)
}