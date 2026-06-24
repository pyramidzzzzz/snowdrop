package site.remlit.snowdrop.util

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.currentLocale

actual fun formatNumber(number: Long): String =
	NSNumberFormatter().apply {
		this.locale = NSLocale.currentLocale
	}.stringFromNumber(NSNumber(long = number))
		?: ""