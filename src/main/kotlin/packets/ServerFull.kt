package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

class ServerFull() : Packet(PacketId.ServerFull) {
	override suspend fun writeTo(writer: Writer) {}


	companion object : CwDeserializer<ServerFull> {
		override suspend fun readFrom(reader: Reader) = ServerFull()
	}
}