package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class Shot(
	val attacker: CreatureId,
	val chunk: Vector2<Int>,
	val unknownA: Int,
	val paddingA: Int,
	val position: Vector3<Long>,
	val unknownV: Vector3<Int>,
	val velocity: Vector3<Float>,
	val legacyDamage: Float,
	val unknownB: Float, //2-4 depending on mana for boomerangs, otherwise 0.5
	val scale: Float,
	val mana: Float,
	val particles: Float,
	val skill: Byte,
	val paddingB: Byte,
	val paddingC: Short,
	val projectile: Projectile,
	val unknownC: Byte,
	val paddingD: Byte,
	val paddingE: Short,
	val unknownD: Float,
	val unknownE: Float
) : Packet(PacketId.Shot), SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(attacker.value)
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
		projectile.writeTo(writer)
		writer.writeByte(unknownC)
		writer.writeByte(paddingD)
		writer.writeShort(paddingE)
		writer.writeFloat(unknownD)
		writer.writeFloat(unknownE)
	}

	companion object : CwDeserializer<Shot> {
		override suspend fun readFrom(reader: Reader) =
			Shot(
				attacker = CreatureId(reader.readLong()),
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
				projectile = Projectile.readFrom(reader),
				unknownC = reader.readByte(),
				paddingD = reader.readByte(),
				paddingE = reader.readShort(),
				unknownD = reader.readFloat(),
				unknownE = reader.readFloat()
			)
	}

	enum class Projectile : CwSerializableEnumInt {
		Zero,
		Arrow,
		Boomerang,
		Unknown,
		Boulder;

		companion object : CwEnumIntDeserializer<Projectile> {
			override val values = values()
		}
	}
}
