package site.remlit.snowdrop.model.cache

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromHexString
import site.remlit.snowdrop.util.config.cbor
import kotlin.time.Clock
import kotlin.time.Instant

@Serializable
data class CacheEntry(
	val id: String,
	val content: String,
	val createdAt: Instant = Clock.System.now()
) {
	@OptIn(ExperimentalSerializationApi::class)
	inline fun <reified T> getContent(): T {
		return cbor.decodeFromHexString<T>(content)
	}
}
