package site.remlit.snowdrop.util

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toBlockingSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import site.remlit.snowdrop.api.verifyCredentials
import site.remlit.snowdrop.model.User

val settingsContext = CoroutineScope(Dispatchers.Default).coroutineContext

@OptIn(ExperimentalSettingsApi::class)
expect val settings: FlowSettings

@OptIn(ExperimentalSettingsApi::class)
val blockingSettings = settings.toBlockingSettings()

fun getCurrentAccountId() = blockingSettings.getString("current_account", "")
fun getCurrentAccountHost() = blockingSettings.getString("account_${getCurrentAccountId()}_host", "")

/**
 * Gets the current account's user object from the verify credentials endpoint.
 * @return User
 * */
@OptIn(ExperimentalSettingsApi::class)
fun getCurrentAccountObjectFlow(): Flow<User> = object : Flow<User> {
	override suspend fun collect(collector: FlowCollector<User>) {
		if (settings.getStringOrNull("account_${getCurrentAccountId()}_user") == null)
			updateCurrentAccountObject()

		val user = json.decodeFromString<User>(
			settings.getString("account_${getCurrentAccountId()}_user", "")
		)

		collector.emit(user)
	}
}

suspend fun updateCurrentAccountObject() {
	val verifyRes = verifyCredentials()
	if (verifyRes.error) return
	if (verifyRes.response !is User) return

	blockingSettings.putString(
		"account_${getCurrentAccountId()}_user",
		json.encodeToString(verifyRes.response)
	)
}