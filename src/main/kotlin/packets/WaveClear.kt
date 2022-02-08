package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

class WaveClear() : Packet(PacketId.WaveClear) {
	override suspend fun writeTo(writer: Writer) {}

	companion object : CwDeserializer<WaveClear> {
		override suspend fun readFrom(reader: Reader) = WaveClear()
	}
}