package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class Projectile(
	val attacker: CreatureId,
	val chunk: Vector2<Int>,
	val unknownA: Int = 0,
	val paddingA: Int = 0,
	val position: Vector3<Long>,
	val unknownV: Vector3<Int> = Vector3(0, 0, 0),
	val velocity: Vector3<Float>,
	val legacyDamage: Float,
	val unknownB: Float = 0.5f, //2-4 depending on mana for boomerangs, otherwise 0.5
	val scale: Float,
	val mana: Float,
	val particles: Float,
	val skill: Byte,
	val paddingB: Byte = 0,
	val paddingC: Short = 0,
	val type: Type,
	val unknownC: Byte = 0,
	val paddingD: Byte = 0,
	val paddingE: Short = 0,
	val unknownD: Float = 0.0f,
	val unknownE: Float = 0.0f
) : Packet(PacketId.Projectile), WorldUpdate.SubPacket {
	override suspend fun writeTo(writer: Writer) {
		attacker.writeTo(writer)
		writer.writeVector2Int(chunk)
		writer.writeInt(unknownA)
		writer.writeInt(paddingA)
		writer.writeVector3Long(position)
		writer.writeVector3Int(unknownV)
		writer.writeVector3Float(velocity)
		writer.writeFloat(legacyDamage)
		writer.writeFloat(unknownB)
		writer.writeFloat(scale)
		writer.writeFloat(mana)
		writer.writeFloat(particles)
		writer.writeByte(skill)
		writer.writeByte(paddingB)
		writer.writeShort(paddingC)
		type.writeTo(writer)
		writer.writeByte(unknownC)
		writer.writeByte(paddingD)
		writer.writeShort(paddingE)
		writer.writeFloat(unknownD)
		writer.writeFloat(unknownE)
	}

	companion object : CwDeserializer<Projectile> {
		override suspend fun readFrom(reader: Reader) =
			Projectile(
				attacker = CreatureId.readFrom(reader),
				chunk = reader.readVector2Int(),
				unknownA = reader.readInt(),
				paddingA = reader.readInt(),
				position = reader.readVector3Long(),
				unknownV = reader.readVector3Int(),
				velocity = reader.readVector3Float(),
				legacyDamage = reader.readFloat(),
				unknownB = reader.readFloat(),
				scale = reader.readFloat(),
				mana = reader.readFloat(),
				particles = reader.readFloat(),
				skill = reader.readByte(),
				paddingB = reader.readByte(),
				paddingC = reader.readShort(),
				type = Type.readFrom(reader),
				unknownC = reader.readByte(),
				paddingD = reader.readByte(),
				paddingE = reader.readShort(),
				unknownD = reader.readFloat(),
				unknownE = reader.readFloat()
			)
	}

	enum class Type : CwSerializableEnumInt {
		Arrow,
		Magic,
		Boomerang,
		Unknown,
		Boulder;

		companion object : CwEnumIntDeserializer<Type> {
			override val values = values()
		}
	}
}
