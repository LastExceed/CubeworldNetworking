package me.lastexceed.cubeworldnetworking.utils

import java.util.zip.Deflater
import java.util.zip.Inflater

object Zlib {
	fun deflate(data: ByteArray): ByteArray {
		// Compress the bytes
		val deflater = Deflater()
		deflater.setInput(data)
		deflater.finish()

		val compressed = ByteArray(5000)//TODO: optimize
		val length = deflater.deflate(compressed)
		deflater.end()

		return compressed.sliceArray(0 until length)
	}

	fun inflate(data: ByteArray): ByteArray {
		// Decompress the bytes
		val inflater = Inflater()
		inflater.setInput(data, 0, data.size)

		val uncompressed = ByteArray(16384)
		val length = inflater.inflate(uncompressed)
		inflater.end()

		return uncompressed.sliceArray(0 until length)
	}
}