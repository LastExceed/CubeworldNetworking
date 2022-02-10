package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class AirTraffic(
	val airships: List<Airship> = emptyList()
) : Packet(PacketId.AirTraffic) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(airships.size)
		airships.forEach {
			it.writeTo(writer)
		}
	}

	companion object : CwDeserializer<AirTraffic> {
		override suspend fun readFrom(reader: Reader) =
			AirTraffic(
				List(reader.readInt()) {
					Airship.readFrom(reader)
				}
			)
	}
}

data class Airship(
	val id: Long,
	val unknownA: Int,
	val unknownB: Int,
	val position: Vector3<Long>,
	val velocity: Vector3<Float>,
	val rotation: Float,
	val station: Vector3<Long>,
	val pathRotation: Float,
	val unknownC: Int,
	val destination: Vector3<Long>,
	val state: State,
	val unknownD: Int
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(id)
		writer.writeInt(unknownA)
		writer.writeInt(unknownB)
		writer.writeVector3Long(position)
		writer.writeVector3Float(velocity)
		writer.writeFloat(rotation)
		writer.writeVector3Long(station)
		writer.writeFloat(pathRotation)
		writer.writeInt(unknownC)
		writer.writeVector3Long(destination)
		state.writeTo(writer)
		writer.writeInt(unknownD)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader): Airship =
			Airship(
				id = reader.readLong(),
				unknownA = reader.readInt(),
				unknownB = reader.readInt(),
				position = reader.readVector3Long(),
				velocity = reader.readVector3Float(),
				rotation = reader.readFloat(),
				station = reader.readVector3Long(),
				pathRotation = reader.readFloat(),
				unknownC = reader.readInt(),
				destination = reader.readVector3Long(),
				state = State.readFrom(reader),
				unknownD = reader.readInt()
			)
	}

	enum class State : CwSerializableEnumInt {
		;//TODO: figure out which airship states exist

		companion object : CwEnumIntDeserializer<State> {
			override val values = values()
		}
	}
}