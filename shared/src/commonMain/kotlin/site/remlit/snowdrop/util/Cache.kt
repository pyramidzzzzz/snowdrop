package site.remlit.snowdrop.util

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toBlockingSettings
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToHexString
import site.remlit.snowdrop.model.cache.CacheEntry
import site.remlit.snowdrop.model.cache.CacheManifest
import site.remlit.snowdrop.util.config.cbor

/* I don't think this will be used, but i'm keeping it just in case */
@OptIn(ExperimentalSettingsApi::class)
expect val cache: FlowSettings

@OptIn(ExperimentalSettingsApi::class)
val blockingCache = cache.toBlockingSettings()


@OptIn(ExperimentalSerializationApi::class)
fun setupCache() {
	if (blockingCache.hasKey("manifest")) return
	blockingCache.putString(
		"manifest",
		cbor.encodeToHexString(CacheManifest())
	)
}

@OptIn(ExperimentalSerializationApi::class)
fun getCacheManifest(): CacheManifest {
	val raw = blockingCache.getStringOrNull("manifest")
		?: return CacheManifest()
	return cbor.decodeFromHexString(raw)
}

@OptIn(ExperimentalSerializationApi::class)
fun getCacheEntry(id: String): CacheEntry? {
	val raw = blockingCache.getStringOrNull("entry_$id")
		?: return null
	return cbor.decodeFromHexString(raw)
}

fun cleanExpiredCacheEntries() {

}
