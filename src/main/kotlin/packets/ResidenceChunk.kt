package me.lastexceed.cubeworldnetworking.packets

import me.lastexceed.cubeworldnetworking.utils.*

data class ResidenceChunk(
	val chunk: Vector2<Int>
) : Packet(PacketId.ChunkDiscovery) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeVector2Int(chunk)
	}

	companion object : CwDeserializer<ResidenceChunk> {
		override suspend fun readFrom(reader: Reader) = ResidenceChunk(reader.readVector2Int())
	}
}