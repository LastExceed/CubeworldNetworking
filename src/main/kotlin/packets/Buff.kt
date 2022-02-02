package me.lastexceed.cubeworldnetworking.packets

import me.lastexceed.cubeworldnetworking.utils.*

data class Buff(
	val source: CreatureID,
	val target: CreatureID,
	val type: Type,
	val modifier: Float,
	val duration: Int,
	val unknownC: Int,
	val creatureID3: CreatureID
) : Packet(Opcode.StatusEffect), SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(source.value)
		writer.writeLong(target.value)
		writer.writeInt(type.value)
		writer.writeFloat(modifier)
		writer.writeInt(duration)
		writer.writeInt(unknownC)
		writer.writeLong(creatureID3.value)
	}

	companion object : CwDeserializer<Buff> {
		override suspend fun readFrom(reader: Reader) =
			Buff(
				source = CreatureID(reader.readLong()),
				target = CreatureID(reader.readLong()),
				type = Type(reader.readInt()),
				modifier = reader.readFloat(),
				duration = reader.readInt(),
				unknownC = reader.readInt(),
				creatureID3 = CreatureID(reader.readLong())
			)
	}

	@JvmInline
	value class Type(val value: Int) {
		companion object {
			val Bulwalk = Type(1)
			val WarFrenzy = Type(2)
			val Camouflage = Type(3)
			val Poison = Type(4)
			val UnknownA = Type(5)
			val ManaShield = Type(6)
			val UnknownB = Type(7)
			val UnknownC = Type(8)
			val FireSpark = Type(9) //fire passive (free insta cast)
			val Intuition = Type(10) //scout passive (insta charge)
			val Elusiveness = Type(11) //ninja passive (25MP + next hit crits
			val Swiftness = Type(12)
		}
	}

//	enum class E : CwSerializable {
//		Bulwalk,
//		WarFrenzy,
//		Camouflage,
//		Poison,
//		UnknownA,
//		ManaShield,
//		UnknownB,
//		UnknownC,
//		FireSpark,
//		Intuition,
//		Elusiveness,
//		Swiftness;
//
//		override suspend fun writeTo(writer: Writer) =
//			writer.writeInt(values().indexOf(this))
//
//		companion object : CwDeserializer<E> {
//			override suspend fun readFrom(reader: Reader) =
//				values()[reader.readInt()]
//		}
//	}
}