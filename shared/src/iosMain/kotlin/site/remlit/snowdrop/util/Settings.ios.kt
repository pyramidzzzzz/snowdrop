package site.remlit.snowdrop.util

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class)
actual val settings: FlowSettings = NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults)
	.toFlowSettings()