package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class StatusEffect(
	val source: CreatureId,
	val target: CreatureId,
	val type: Type,
	val paddingA: Byte = 0,
	val paddingB: Short = 0,
	val modifier: Float,
	val duration: Int,
	val paddingC: Int = 0,
	val creatureId3: CreatureId
) : Packet(PacketId.StatusEffect) {
	override suspend fun writeTo(writer: Writer) {
		source.writeTo(writer)
		target.writeTo(writer)
		type.writeTo(writer)
		writer.writeByte(paddingA)
		writer.writeShort(paddingB)
		writer.writeFloat(modifier)
		writer.writeInt(duration)
		writer.writeInt(paddingC)
		creatureId3.writeTo(writer)
	}

	companion object : CwDeserializer<StatusEffect> {
		override suspend fun readFrom(reader: Reader) =
			StatusEffect(
				source = CreatureId.readFrom(reader),
				target = CreatureId.readFrom(reader),
				type = Type.readFrom(reader),
				paddingA = reader.readByte(),
				paddingB = reader.readShort(),
				modifier = reader.readFloat(),
				duration = reader.readInt(),
				paddingC = reader.readInt(),
				creatureId3 = CreatureId.readFrom(reader)
			)
	}

	enum class Type : CwSerializableEnumByte {
		Zero,
		Bulwalk,
		WarFrenzy,
		Camouflage,
		Poison,
		Unknown5,
		ManaShield,
		Unknown7,
		Unknown8,
		FireSpark,
		Intuition,
		Elusiveness,
		Swiftness;

		companion object : CwEnumByteDeserializer<Type> {
			override val values = values()
		}
	}
}