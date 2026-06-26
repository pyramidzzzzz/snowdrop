package site.remlit.snowdrop.util.cache

import android.content.Context
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.SharedPreferencesSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import site.remlit.snowdrop.util.AndroidContext


@OptIn(ExperimentalSettingsApi::class)
actual val cache: FlowSettings = SharedPreferencesSettings(
	AndroidContext.context
		.getSharedPreferences("snowdrop_cache", Context.MODE_PRIVATE)
).toFlowSettings()
