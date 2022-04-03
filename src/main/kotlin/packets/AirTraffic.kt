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
	val unknownA: Byte,
	val paddingA: Byte,
	val paddingB: Short,
	val paddingC: Int,
	val position: Vector3<Long>,
	val velocity: Vector3<Float>,
	val rotation: Float,
	val station: Vector3<Long>,
	val pathRotation: Float,
	val paddingD: Int,
	val destination: Vector3<Long>,
	val state: State,
	val paddingE: Byte,
	val paddingF: Short,
	val paddingG: Int
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(id)
		writer.writeByte(unknownA)
		writer.writeByte(paddingA)
		writer.writeShort(paddingB)
		writer.writeInt(paddingC)
		writer.writeVector3Long(position)
		writer.writeVector3Float(velocity)
		writer.writeFloat(rotation)
		writer.writeVector3Long(station)
		writer.writeFloat(pathRotation)
		writer.writeInt(paddingD)
		writer.writeVector3Long(destination)
		state.writeTo(writer)
		writer.writeByte(paddingE)
		writer.writeShort(paddingF)
		writer.writeInt(paddingG)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader): Airship =
			Airship(
				id = reader.readLong(),
				unknownA = reader.readByte(),
				paddingA = reader.readByte(),
				paddingB = reader.readShort(),
				paddingC = reader.readInt(),
				position = reader.readVector3Long(),
				velocity = reader.readVector3Float(),
				rotation = reader.readFloat(),
				station = reader.readVector3Long(),
				pathRotation = reader.readFloat(),
				paddingD = reader.readInt(),
				destination = reader.readVector3Long(),
				state = State.readFrom(reader),
				paddingE = reader.readByte(),
				paddingF = reader.readShort(),
				paddingG = reader.readInt()
			)
	}

	enum class State : CwSerializableEnumByte {
		;//TODO: figure out which airship states exist

		companion object : CwEnumByteDeserializer<State> {
			override val values = values()
		}
	}
}