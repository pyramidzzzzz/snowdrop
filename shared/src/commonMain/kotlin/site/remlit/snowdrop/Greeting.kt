package site.remlit.snowdrop

class Greeting {
	private val platform = getPlatform()

	fun greet(): String {
		return sayHello(platform.name)
	}
}