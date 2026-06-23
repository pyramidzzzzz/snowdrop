package site.remlit.snowdrop

interface Platform {
	val name: String
}

expect fun getPlatform(): Platform