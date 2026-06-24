package site.remlit.snowdrop.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
	val error: Boolean = false,
	val message: String? = null,
	val response: T? = null
)
