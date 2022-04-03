package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class StatusEffect(
	val source: CreatureId,
	val target: CreatureId,
	val type: Type,
	val paddingA: Byte,
	val paddingB: Short,
	val modifier: Float,
	val duration: Int,
	val paddingC: Int,
	val creatureId3: CreatureId
) : Packet(PacketId.StatusEffect), SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(source.value)
		writer.writeLong(target.value)
		type.writeTo(writer)
		writer.writeByte(paddingA)
		writer.writeShort(paddingB)
		writer.writeFloat(modifier)
		writer.writeInt(duration)
		writer.writeInt(paddingC)
		writer.writeLong(creatureId3.value)
	}

	companion object : CwDeserializer<StatusEffect> {
		override suspend fun readFrom(reader: Reader) =
			StatusEffect(
				source = CreatureId(reader.readLong()),
				target = CreatureId(reader.readLong()),
				type = Type.readFrom(reader),
				paddingA = reader.readByte(),
				paddingB = reader.readShort(),
				modifier = reader.readFloat(),
				duration = reader.readInt(),
				paddingC = reader.readInt(),
				creatureId3 = CreatureId(reader.readLong())
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