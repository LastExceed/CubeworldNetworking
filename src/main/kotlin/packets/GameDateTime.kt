package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class GameDateTime(
	val day: Int,
	val time: Int
) : Packet(PacketId.GameDateTime) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(day)
		writer.writeInt(time)
	}

	companion object : CwDeserializer<GameDateTime> {
		override suspend fun readFrom(reader: Reader) =
			GameDateTime(
				day = reader.readInt(),
				time = reader.readInt()
			)
	}
}