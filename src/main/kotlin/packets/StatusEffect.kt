package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class StatusEffect(
	val source: CreatureID,
	val target: CreatureID,
	val type: Type,
	val unknownA: Byte,
	val unknownB: Short,//most likely padding, content seems to be garbage data
	val modifier: Float,
	val duration: Int,
	val unknownC: Int,
	val creatureID3: CreatureID
) : Packet(PacketId.StatusEffect), SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(source.value)
		writer.writeLong(target.value)
		type.writeTo(writer)
		writer.writeFloat(modifier)
		writer.writeInt(duration)
		writer.writeInt(unknownC)
		writer.writeLong(creatureID3.value)
	}

	companion object : CwDeserializer<StatusEffect> {
		override suspend fun readFrom(reader: Reader) =
			StatusEffect(
				source = CreatureID(reader.readLong()),
				target = CreatureID(reader.readLong()),
				type = Type.readFrom(reader),
				unknownA = reader.readByte(),
				unknownB = reader.readShort(),
				modifier = reader.readFloat(),
				duration = reader.readInt(),
				unknownC = reader.readInt(),
				creatureID3 = CreatureID(reader.readLong())
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