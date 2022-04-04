package com.github.lastexceed.cubeworldnetworking.utils

import io.ktor.util.moveToByteArray
import io.ktor.utils.io.*
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.nio.*

interface Writer {
	suspend fun writeByte(value: Byte)
	suspend fun writeShort(value: Short)
	suspend fun writeInt(value: Int)
	suspend fun writeFloat(value: Float)
	suspend fun writeLong(value: Long)
	suspend fun writeByteArray(data: ByteArray)
	suspend fun writeBoolean(value: Boolean)

	suspend fun pad(count: Int)
}

@JvmInline
value class ByteWriteChannelAdapter(private val inner: ByteWriteChannel) : Writer {
	override suspend fun writeByte(value: Byte) = inner.writeByte(value)
	override suspend fun writeShort(value: Short) = inner.writeShortLittleEndian(value)
	override suspend fun writeInt(value: Int) = inner.writeIntLittleEndian(value)
	override suspend fun writeFloat(value: Float) = inner.writeFloatLittleEndian(value)
	override suspend fun writeLong(value: Long) = inner.writeLongLittleEndian(value)
	override suspend fun writeByteArray(data: ByteArray) = inner.writeFully(data)
	override suspend fun writeBoolean(value: Boolean) = inner.writeBoolean(value)

	override suspend fun pad(count: Int) = writeByteArray(ByteArray(count))
}

@JvmInline
value class ByteBufferAdapter(private val inner: ByteBuffer) : Writer {
	override suspend fun writeByte(value: Byte) { inner.put(value) }
	override suspend fun writeShort(value: Short) { inner.putShort(value) }
	override suspend fun writeInt(value: Int) { inner.putInt(value) }
	override suspend fun writeFloat(value: Float) { inner.putFloat(value) }
	override suspend fun writeLong(value: Long) { inner.putLong(value) }
	override suspend fun writeByteArray(data: ByteArray) { inner.put(data) }
	override suspend fun writeBoolean(value: Boolean) { inner.put(if (value) 1 else 0) }

	override suspend fun pad(count: Int) { inner.position(inner.position() + count) }
}