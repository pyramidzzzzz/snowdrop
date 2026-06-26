package site.remlit.snowdrop.util.cache

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class)
actual val cache: FlowSettings = NSUserDefaultsSettings(NSUserDefaults(suiteName = "snowdrop_cache"))
	.toFlowSettings()
