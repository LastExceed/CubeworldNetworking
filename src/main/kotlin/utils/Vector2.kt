package me.lastexceed.cubeworldnetworking.utils

data class Vector2<NumericType : Number>(
	val x: NumericType,
	val y: NumericType
)

internal suspend fun Reader.readVector2Int() =
	Vector2(
		readInt(),
		readInt()
	)

internal suspend fun Writer.writeVector2Int(vector: Vector2<Int>) {
	writeInt(vector.x)
	writeInt(vector.y)
}