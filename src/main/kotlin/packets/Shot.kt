package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class Shot(
	val attacker: Long,
	val chunk: Vector2<Int>,
	val unknownA: Int,
	val paddingA: Int,
	val position: Vector3<Long>,
	val unknownV: Vector3<Int>,
	val velocity: Vector3<Float>,
	val legacyDamage: Float,
	val unknownB: Float,
	val scale: Float,
	val mana: Float,
	val particles: Float,
	val skill: Int,
	val projectile: Projectile,
	val paddingB: Int,
	val unknownC: Float,
	val unknownD: Float
) : Packet(PacketId.Shot), SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(attacker)
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
		writer.writeInt(skill)
		projectile.writeTo(writer)
		writer.writeInt(paddingB)
		writer.writeFloat(unknownC)
		writer.writeFloat(unknownD)
	}

	companion object : CwDeserializer<Shot> {
		override suspend fun readFrom(reader: Reader) =
			Shot(
				attacker = reader.readLong(),
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
				skill = reader.readInt(),
				projectile = Projectile.readFrom(reader),
				paddingB = reader.readInt(),
				unknownC = reader.readFloat(),
				unknownD = reader.readFloat()
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
