package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class ProtocolVersion(
	val version: Int
) : Packet(PacketId.ProtocolVersion) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(version)
	}

	companion object : CwDeserializer<ProtocolVersion> {
		override suspend fun readFrom(reader: Reader) = ProtocolVersion(reader.readInt())
	}
}