package site.remlit.snowdrop.model.cache

import kotlinx.serialization.Serializable
import site.remlit.snowdrop.util.config.json
import kotlin.time.Clock
import kotlin.time.Instant

@Serializable
data class CacheEntry(
	val id: String,
	val content: String,
	val createdAt: Instant = Clock.System.now()
) {
	inline fun <reified T> getContent(): T {
		return json.decodeFromString<T>(content)
	}
}
