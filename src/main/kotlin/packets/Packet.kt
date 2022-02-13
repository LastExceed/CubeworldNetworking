package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

abstract class Packet(val packetId: PacketId) : CwSerializable

enum class PacketId : CwSerializableEnumInt {
	CreatureUpdate,
	MultiEntityUpdate,
	WaveClear,
	AirTraffic,
	ServerUpdate,
	Time,
	CreatureAction,
	Hit,
	StatusEffect,
	Shot,
	ChatMessage,
	ChunkDiscovery,
	SectorDiscovery,
	Unknown13,
	Unknown14,
	MapSeed,
	Join,
	ProtocolVersion,
	ServerFull;

	companion object : CwEnumIntDeserializer<PacketId> {
		override val values = values()
	}
}

interface SubPacket : CwSerializable

interface CwSerializable {
	suspend fun writeTo(writer: Writer)
}

interface CwSerializableEnum : CwSerializable {
	val ordinal: Int
}

interface CwSerializableEnumByte : CwSerializableEnum {
	override suspend fun writeTo(writer: Writer) {
		writer.writeByte(ordinal.toByte())
	}
}

interface CwSerializableEnumInt : CwSerializableEnum {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(ordinal)
	}
}

interface CwDeserializer<T> {
	suspend fun readFrom(reader: Reader): T
}

interface CwEnumDeserializer<T : CwSerializableEnum> : CwDeserializer<T> {
	val values: Array<T> //values() is synthetic so it can't be accessed inside the interface. workaround until https://youtrack.jetbrains.com/issue/KT-11968 is implemented
}

interface CwEnumByteDeserializer<T : CwSerializableEnumByte> : CwEnumDeserializer<T> {
	override suspend fun readFrom(reader: Reader) =
		values[reader.readByte().toInt()]
}

interface CwEnumIntDeserializer<T : CwSerializableEnumInt> : CwEnumDeserializer<T> {
	override suspend fun readFrom(reader: Reader) =
		values[reader.readInt()]
}