package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class Hit(
	val attacker: CreatureId,
	val target: CreatureId,
	val damage: Float,
	val critical: Boolean,
	val paddingA: Byte = 0,
	val paddingB: Short = 0,
	val stuntime: Int,
	val paddingC: Int = 0,//todo: cuwo says this is something
	val position: Vector3<Long>,
	val direction: Vector3<Float>,
	val isYellow: Boolean,
	val type: Type,
	val flash: Boolean,
	val paddingD: Byte = 0
) : Packet(PacketId.Hit), WorldUpdate.SubPacket {
	override suspend fun writeTo(writer: Writer) {
		attacker.writeTo(writer)
		target.writeTo(writer)
		writer.writeFloat(damage)
		writer.writeBoolean(critical)
		writer.writeByte(paddingA)
		writer.writeShort(paddingB)
		writer.writeInt(stuntime)
		writer.writeInt(paddingC)
		writer.writeVector3Long(position)
		writer.writeVector3Float(direction)
		writer.writeBoolean(isYellow)
		type.writeTo(writer)
		writer.writeBoolean(flash)
		writer.writeByte(paddingD)
	}

	companion object : CwDeserializer<Hit> {
		override suspend fun readFrom(reader: Reader) =
			Hit(
				attacker = CreatureId.readFrom(reader),
				target = CreatureId.readFrom(reader),
				damage = reader.readFloat(),
				critical = reader.readBoolean(),
				paddingA = reader.readByte(),
				paddingB = reader.readShort(),
				stuntime = reader.readInt(),
				paddingC = reader.readInt(),
				position = reader.readVector3Long(),
				direction = reader.readVector3Float(),
				isYellow = reader.readBoolean(),
				type = Type.readFrom(reader),
				flash = reader.readBoolean(),
				paddingD = reader.readByte()
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