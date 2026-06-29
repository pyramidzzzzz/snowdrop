package site.remlit.snowdrop.util.config

import android.os.Build.VERSION.SDK_INT
import coil3.decode.Decoder
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder

actual fun getGifDecoder(): Decoder.Factory =
	if (SDK_INT >= 28) AnimatedImageDecoder.Factory()
	else GifDecoder.Factory()
