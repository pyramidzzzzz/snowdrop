package site.remlit.snowdrop.util

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toBlockingSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

val settingsContext = CoroutineScope(Dispatchers.Default).coroutineContext

@OptIn(ExperimentalSettingsApi::class)
expect val settings: FlowSettings

@OptIn(ExperimentalSettingsApi::class)
val blockingSettings = settings.toBlockingSettings()

fun getCurrentAccountId() = blockingSettings.getString("current_account", "")
fun getCurrentAccountHost() = blockingSettings.getString("account_${getCurrentAccountId()}_host", "")