package me.lastexceed.cubeworldnetworking.utils

import java.util.zip.Deflater
import java.util.zip.Inflater

object Zlib {//todo: don't reinstantiate everything every time
	fun deflate(data: ByteArray) =
		Deflater().run {
			setInput(data)
			finish()

			val compressed = ByteArray(5000)//TODO: optimize
			val length = deflate(compressed)
			end()

			compressed.sliceArray(0 until length)
		}

	fun inflate(data: ByteArray) =
		Inflater().run {
			setInput(data, 0, data.size)

			val uncompressed = ByteArray(16384)
			val length = inflate(uncompressed)
			end()

			uncompressed.sliceArray(0 until length)
		}
}