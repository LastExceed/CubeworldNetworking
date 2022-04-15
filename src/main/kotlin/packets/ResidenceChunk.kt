package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class ResidenceChunk(
	val chunk: Vector2<Int>
) : Packet(PacketId.ResidenceChunk) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeVector2Int(chunk)
	}

	companion object : CwDeserializer<ResidenceChunk> {
		override suspend fun readFrom(reader: Reader) = ResidenceChunk(reader.readVector2Int())
	}
}