package site.remlit.snowdrop.model.cache

import kotlinx.serialization.Serializable

@Serializable
data class CacheManifest(
	val ids: List<String> = emptyList()
)
