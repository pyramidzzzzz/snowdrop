package site.remlit.snowdrop.api.oauth

import com.russhwolf.settings.ExperimentalSettingsApi
import io.ktor.client.request.forms.submitForm
import io.ktor.http.headers
import site.remlit.snowdrop.model.ApiResponse
import site.remlit.snowdrop.model.response.OauthToken
import site.remlit.snowdrop.util.endOfRequest
import site.remlit.snowdrop.util.getCurrentAccountHost
import site.remlit.snowdrop.util.getCurrentAccountId
import site.remlit.snowdrop.util.httpClient
import site.remlit.snowdrop.util.settings

@OptIn(ExperimentalSettingsApi::class)
suspend fun verifyCredentials(): ApiResponse<OauthToken> {
	val accountId = getCurrentAccountId()
	val host = getCurrentAccountHost()
	val token = settings.getString("account_${accountId}_token", "")

	val req = httpClient.submitForm("https://$host/api/v1/accounts/verify_credentials") {
		headers { append("Authorization", "Bearer $token") }
	}

	return endOfRequest(req)
}