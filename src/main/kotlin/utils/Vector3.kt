package com.github.lastexceed.cubeworldnetworking.utils

data class Vector3<NumericType : Number>(
	val x: NumericType,
	val y: NumericType,
	val z: NumericType
)

internal suspend fun Reader.readVector3Byte() =
	Vector3(
		x = readByte(),
		y = readByte(),
		z = readByte()
	)

internal suspend fun Reader.readVector3Int() =
	Vector3(
		x = readInt(),
		y = readInt(),
		z = readInt()
	)

internal suspend fun Reader.readVector3Float() =
	Vector3(
		x = readFloat(),
		y = readFloat(),
		z = readFloat()
	)

internal suspend fun Reader.readVector3Long() =
	Vector3(
		x = readLong(),
		y = readLong(),
		z = readLong()
	)
internal suspend fun Writer.writeVector3Byte(vector: Vector3<Byte>) {
	listOf(
		vector.x,
		vector.y,
		vector.z
	).forEach { writeByte(it) }
}

internal suspend fun Writer.writeVector3Int(vector: Vector3<Int>) {
	listOf(
		vector.x,
		vector.y,
		vector.z
	).forEach { writeInt(it) }
}

internal suspend fun Writer.writeVector3Float(vector: Vector3<Float>) {
	listOf(
		vector.x,
		vector.y,
		vector.z
	).forEach { writeFloat(it) }
}

internal suspend fun Writer.writeVector3Long(vector: Vector3<Long>) {
	listOf(
		vector.x,
		vector.y,
		vector.z
	).forEach { writeLong(it) }
}