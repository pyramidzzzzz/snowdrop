package site.remlit.snowdrop.util.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import site.remlit.snowdrop.api.accounts.getAccount
import site.remlit.snowdrop.model.Account
import site.remlit.snowdrop.util.safe

/**
 * Gets the cached representation of an account (if available) before
 * the request to get a fresh version finishes.
 * */
fun fetchAccount(id: String): Flow<Account> = object : Flow<Account> {
	override suspend fun collect(collector: FlowCollector<Account>) = safe {
		val cached = getCacheEntry("account_$id")
		if (cached != null) safe {
			collector.emit(cached.getContent<Account>())
		}

		val req = getAccount(id)
		if (!req.error && req.response != null)
			collector.emit(req.response)

		putCacheEntry("account_$id", req.response)
	}
}
