package com.github.lastexceed.cubeworldnetworking.utils

import java.util.zip.*

object Zlib {
	fun deflate(data: ByteArray) = DeflaterInputStream(data.inputStream()).readAllBytes()
	fun inflate(data: ByteArray) = InflaterInputStream(data.inputStream()).readAllBytes()
}