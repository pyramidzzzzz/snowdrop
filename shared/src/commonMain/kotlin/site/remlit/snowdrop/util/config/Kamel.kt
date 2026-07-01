package site.remlit.snowdrop.util.config

import androidx.compose.animation.core.tween
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.httpUrlFetcher
import io.kamel.core.config.takeFrom
import io.kamel.image.config.animatedImageDecoder
import io.kamel.image.config.Default

val kamelConfig = KamelConfig {
	takeFrom(KamelConfig.Default)

	animatedImageDecoder()

	httpUrlFetcher {
		// 100 MiB
		httpCache(100 * 1024 * 1024)
	}
}
