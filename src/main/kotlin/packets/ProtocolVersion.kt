package me.lastexceed.cubeworldnetworking.packets

import me.lastexceed.cubeworldnetworking.utils.*

data class ProtocolVersion(
	val version: Int
) : Packet(Opcode.ProtocolVersion) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(version)
	}

	companion object : CwDeserializer<ProtocolVersion> {
		override suspend fun readFrom(reader: Reader): ProtocolVersion {
			return ProtocolVersion(reader.readInt())
		}
	}
}