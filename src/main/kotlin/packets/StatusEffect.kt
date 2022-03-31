package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class StatusEffect(
	val source: CreatureId,
	val target: CreatureId,
	val type: Type,
	val unknownA: Byte,
	val unknownB: Short,//most likely padding, content seems to be garbage data
	val modifier: Float,
	val duration: Int,
	val unknownC: Int,
	val creatureId3: CreatureId
) : Packet(PacketId.StatusEffect), SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(source.value)
		writer.writeLong(target.value)
		type.writeTo(writer)
		writer.writeByte(unknownA)
		writer.writeShort(unknownB)
		writer.writeFloat(modifier)
		writer.writeInt(duration)
		writer.writeInt(unknownC)
		writer.writeLong(creatureId3.value)
	}

	companion object : CwDeserializer<StatusEffect> {
		override suspend fun readFrom(reader: Reader) =
			StatusEffect(
				source = CreatureId(reader.readLong()),
				target = CreatureId(reader.readLong()),
				type = Type.readFrom(reader),
				unknownA = reader.readByte(),
				unknownB = reader.readShort(),
				modifier = reader.readFloat(),
				duration = reader.readInt(),
				unknownC = reader.readInt(),
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