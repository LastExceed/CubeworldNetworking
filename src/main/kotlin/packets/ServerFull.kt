package me.lastexceed.cubeworldnetworking.packets

import me.lastexceed.cubeworldnetworking.utils.*

class ServerFull() : Packet(Opcode.ServerFull) {
	override suspend fun writeTo(writer: Writer) {}


	companion object : CwDeserializer<ServerFull> {
		override suspend fun readFrom(reader: Reader) = ServerFull()
	}
}