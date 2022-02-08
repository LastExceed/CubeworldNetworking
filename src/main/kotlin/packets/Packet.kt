package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

abstract class Packet(val packetId: PacketId) : CwSerializable

@JvmInline
value class PacketId(val value: Int) {
	companion object {
		val CreatureUpdate = PacketId(0)
		val MultiEntityUpdate = PacketId(1)
		val WaveClear = PacketId(2)
		val AirTraffic = PacketId(3)
		val ServerUpdate = PacketId(4)
		val Time = PacketId(5)
		val CreatureAction = PacketId(6)
		val Hit = PacketId(7)
		val StatusEffect = PacketId(8)
		val Shot = PacketId(9)
		val ChatMessage = PacketId(10)
		val ChunkDiscovery = PacketId(11)
		val SectorDiscovery = PacketId(12)
		val Unknown13 = PacketId(13)
		val Unknown14 = PacketId(14)
		val MapSeed = PacketId(15)
		val Join = PacketId(16)
		val ProtocolVersion = PacketId(17)
		val ServerFull = PacketId(18)
	}
}

interface SubPacket : CwSerializable

interface CwSerializable {
	suspend fun writeTo(writer: Writer)
}

interface CwDeserializer<T> {
	suspend fun readFrom(reader: Reader): T
}