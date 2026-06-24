package site.remlit.snowdrop.util

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import site.remlit.snowdrop.model.ApiResponse

val httpClient = HttpClient {
	install(ContentNegotiation) {
		json(Json {
			prettyPrint = true
			isLenient = true
			ignoreUnknownKeys = true
		})
	}
}

suspend inline fun <reified T> endOfRequest(req: HttpResponse): ApiResponse<T> {
	if (!req.status.isSuccess()) {
		val error = "${req.status.value} - ${req.request.url}" +
				"\nBody: ${req.bodyAsText()}"
		Logger.e { error }
		return ApiResponse(true, error)
	}

	val res = ApiResponse(response = req.body<T>())
	Logger.d {
		"${req.status.value} - ${req.request.url}" +
			"\nApiResponse: $res"
	}
	return res
}