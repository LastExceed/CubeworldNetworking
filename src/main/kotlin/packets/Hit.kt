package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class Hit(
	val attacker: CreatureId,
	val target: CreatureId,
	val damage: Float,
	val critical: Boolean,
	val stuntime: Int,
	val paddingA: Int,
	val position: Vector3<Long>,
	val direction: Vector3<Float>,
	val isYellow: Boolean,
	val type: Type,
	val flash: Boolean,
	val paddingB: Byte
) : Packet(PacketId.Hit), SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(attacker.value)
		writer.writeLong(target.value)
		writer.writeFloat(damage)
		writer.writeBoolean(critical); writer.pad(3)
		writer.writeInt(stuntime)
		writer.writeInt(paddingA)
		writer.writeVector3Long(position)
		writer.writeVector3Float(direction)
		writer.writeBoolean(isYellow)
		type.writeTo(writer)
		writer.writeBoolean(flash)
		writer.writeByte(paddingB)
	}

	companion object : CwDeserializer<Hit> {
		override suspend fun readFrom(reader: Reader) =
			Hit(
				attacker = CreatureId(reader.readLong()),
				target = CreatureId(reader.readLong()),
				damage = reader.readFloat(),
				critical = reader.readInt() > 0,
				stuntime = reader.readInt(),
				paddingA = reader.readInt(),
				position = reader.readVector3Long(),
				direction = reader.readVector3Float(),
				isYellow = reader.readBoolean(),
				type = Type.readFrom(reader),
				flash = reader.readBoolean(),
				paddingB = reader.readByte()
			)
	}

	enum class Type : CwSerializableEnumByte {
		Default,
		Block,
		Unknown,
		Miss,
		Dodge,
		Absorb,
		Invisible;

		companion object : CwEnumByteDeserializer<Type> {
			override val values = values()
		}
	}
}