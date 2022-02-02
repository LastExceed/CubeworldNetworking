package me.lastexceed.cubeworldnetworking.packets

import me.lastexceed.cubeworldnetworking.utils.*

data class ResidenceBiome(
	val sector: Vector2<Int>
) : Packet(Opcode.ChunkDiscovery) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeVector2Int(sector)
	}

	companion object : CwDeserializer<ResidenceBiome> {
		override suspend fun readFrom(reader: Reader) = ResidenceBiome(reader.readVector2Int())
	}
}