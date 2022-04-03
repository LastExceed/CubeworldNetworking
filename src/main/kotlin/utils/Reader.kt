package com.github.lastexceed.cubeworldnetworking.utils

import io.ktor.utils.io.*

@JvmInline
value class Reader(private val inner: ByteReadChannel) {
	constructor(data: ByteArray) : this(ByteReadChannel(data))

	suspend fun readByte(): Byte = inner.readByte()
	suspend fun readShort(): Short = inner.readShortLittleEndian()
	suspend fun readInt(): Int = inner.readIntLittleEndian()
	suspend fun readFloat(): Float = inner.readFloatLittleEndian()
	suspend fun readLong(): Long = inner.readLongLittleEndian()
	suspend fun readBoolean(): Boolean = inner.readBoolean()
	suspend fun readByteArray(count: Int) =
		ByteArray(count).apply {
			inner.readFully(this, 0, count)
		}

	suspend fun skip(count: Int) {
		inner.discard(count.toLong())
	}
}