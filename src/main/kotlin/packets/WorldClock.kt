package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class WorldClock(
	val day: Int,
	val time: Int
) : Packet(PacketId.WorldClock) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(day)
		writer.writeInt(time)
	}

	companion object : CwDeserializer<WorldClock> {
		override suspend fun readFrom(reader: Reader) =
			WorldClock(
				day = reader.readInt(),
				time = reader.readInt()
			)
	}
}