package me.lastexceed.cubeworldnetworking.packets

import me.lastexceed.cubeworldnetworking.utils.*

class WaveClear() : Packet(Opcode.WaveClear) {
	override suspend fun writeTo(writer: Writer) {}

	companion object : CwDeserializer<WaveClear> {
		override suspend fun readFrom(reader: Reader) = WaveClear()
	}
}