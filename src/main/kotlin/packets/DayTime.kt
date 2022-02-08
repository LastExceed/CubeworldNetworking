package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class DayTime(
	val day: Int,
	val time: Int
) : Packet(PacketId.Time) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(day)
		writer.writeInt(time)
	}

	companion object : CwDeserializer<DayTime> {
		override suspend fun readFrom(reader: Reader) =
			DayTime(
				day = reader.readInt(),
				time = reader.readInt()
			)
	}
}