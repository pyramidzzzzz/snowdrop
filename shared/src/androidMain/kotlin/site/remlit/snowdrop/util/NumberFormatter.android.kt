package site.remlit.snowdrop.util

import java.text.NumberFormat

actual fun formatNumber(number: Long): String =
	NumberFormat.getInstance().format(number)