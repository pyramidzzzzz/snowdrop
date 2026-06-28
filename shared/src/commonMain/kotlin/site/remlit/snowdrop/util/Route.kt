package site.remlit.snowdrop.util

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute

inline fun <reified T : Any> atRoute(
	c: NavDestination?
): Boolean {
	if (c == null) return false
	return c.hasRoute<T>()
}
