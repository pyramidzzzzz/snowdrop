package site.remlit.snowdrop.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Object of mutable states which advertise the current supported
 * features of the logged in account.
 * */
object Features {
	var supportsReactions by mutableStateOf(false)

	suspend fun determineFeatures() {

	}

	fun reset() {
		supportsReactions = false
	}
}
