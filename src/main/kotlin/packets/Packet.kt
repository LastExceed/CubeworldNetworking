package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

sealed class Packet(val packetId: PacketId) : CwSerializable

enum class PacketId : CwSerializableEnumInt {
	CreatureUpdate,
	MultiEntityUpdate,
	ServerTick,
	Airships,
	WorldUpdate,
	GameDateTime,
	CreatureAction,
	Hit,
	StatusEffect,
	Projectile,
	ChatMessage,
	ResidenceChunk,
	ResidenceBiome,
	Unknown13,
	Unknown14,
	MapSeed,
	PlayerInitialization,
	ProtocolVersion,
	ServerFull;

	companion object : CwEnumIntDeserializer<PacketId> {
		override val values = values()
	}
}

interface CwSerializable {
	suspend fun writeTo(writer: Writer)
}

interface CwSerializableEnum : CwSerializable {
	val ordinal: Int
}

interface CwSerializableEnumByte : CwSerializableEnum {
	val serialized: Byte
		get() = ordinal.toByte()

	override suspend fun writeTo(writer: Writer) {
		writer.writeByte(serialized)
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
		reader.readByte().let { foo ->
			values.find { it.serialized == foo } ?: error("unknown enum value $foo")
		}

}

interface CwEnumIntDeserializer<T : CwSerializableEnumInt> : CwEnumDeserializer<T> {
	override suspend fun readFrom(reader: Reader) =
		values[reader.readInt()]
}