package site.remlit.snowdrop.util

import android.content.Context
import androidx.startup.Initializer

class ContextInitializer : Initializer<AndroidContext> {
	override fun create(context: Context): AndroidContext {
		return AndroidContext.apply { this.context = context }
	}

	override fun dependencies(): List<Class<out Initializer<*>?>?> {
		return emptyList()
	}
}