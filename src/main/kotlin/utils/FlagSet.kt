package me.lastexceed.cubeworldnetworking.utils

class FlagSet<IndexType : FlagSetIndex>(internal val inner: BooleanArray) {
	operator fun get(index: IndexType) = inner[index.value]

	operator fun set(index: IndexType, value: Boolean) {
		inner[index.value] = value
	}

	override fun equals(other: Any?) =
		when {
			this === other -> true
			other !is FlagSet<*> -> false
			else -> inner.contentEquals(other.inner)
		}

	override fun hashCode() = inner.hashCode()
}

interface FlagSetIndex {
	val value: Int
}


fun Byte.toBooleanArray() =
	BooleanArray(Byte.SIZE_BITS) {
		(toInt() shr it) and 1 != 0
	}

fun Short.toBooleanArray() =
	BooleanArray(Short.SIZE_BITS) {
		(toInt() shr it) and 1 != 0
	}

fun Int.toBooleanArray() =
	BooleanArray(Int.SIZE_BITS) {
		(this shr it) and 1 != 0
	}

fun Long.toBooleanArray() =
	BooleanArray(Long.SIZE_BITS) {
		(this shr it) and 1 != 0L
	}


fun BooleanArray.toByte() =
	foldIndexed(0) { index, accumulator, value ->
		accumulator + (if (value) 1 shl index else 0)
	}.toByte()

fun BooleanArray.toShort() =
	foldIndexed(0) { index, accumulator, value ->
		accumulator + (if (value) 1 shl index else 0)
	}.toShort()

fun BooleanArray.toInt() =
	foldIndexed(0) { index, accumulator, value ->
		accumulator + (if (value) 1 shl index else 0)
	}

fun BooleanArray.toLong() =
	foldIndexed(0L) { index, accumulator, value ->
		accumulator + (if (value) 1L shl index else 0)
	}