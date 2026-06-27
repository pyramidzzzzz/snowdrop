package site.remlit.snowdrop.model

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import site.remlit.snowdrop.util.SnackbarController
import site.remlit.snowdrop.util.bg

/**
 * @param error If an error occurred
 * @param message Error message, if any error
 * @param response Returns specified T, but Unit if no response body
 * */
@Serializable
data class ApiResponse<T>(
	val error: Boolean = false,
	val message: String? = null,
	val response: T? = null
) {
	fun handleError(snackbarController: SnackbarHostState) = bg {
		// todo: pass route to this somehow
		snackbarController.showSnackbar("Error: $message")
	}
}
