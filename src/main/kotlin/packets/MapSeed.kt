package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

class MapSeed(
	val seed: Int
) : Packet(PacketId.MapSeed) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(seed)
	}

	companion object : CwDeserializer<MapSeed> {
		override suspend fun readFrom(reader: Reader) = MapSeed(reader.readInt())
	}
}