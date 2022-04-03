package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

class ServerTick : Packet(PacketId.ServerTick) {
	override suspend fun writeTo(writer: Writer) {}

	companion object : CwDeserializer<ServerTick> {
		override suspend fun readFrom(reader: Reader) = ServerTick()
	}
}