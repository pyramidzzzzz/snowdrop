package site.remlit.snowdrop.util

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import site.remlit.snowdrop.model.ui.Destination

inline fun <reified T : Destination> atRoute(
	c: NavDestination?
): Boolean {
	if (c == null) return false
	return c.hasRoute<T>()
}