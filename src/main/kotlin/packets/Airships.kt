package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

class Airships(
	airships: List<Airship> = emptyList()
) : Packet(PacketId.Airships), List<Airship> by airships {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(size)
		forEach {
			it.writeTo(writer)
		}
	}

	companion object : CwDeserializer<Airships> {
		override suspend fun readFrom(reader: Reader) =
			Airships(
				List(reader.readInt()) {
					Airship.readFrom(reader)
				}
			)
	}
}

data class Airship(
	val id: Id,
	val unknownA: Byte = 0,
	val paddingA: Byte = 0,
	val paddingB: Short = 0,
	val paddingC: Int = 0,
	val position: Vector3<Long>,
	val velocity: Vector3<Float>,
	val rotation: Float,
	val station: Vector3<Long>,
	val pathRotation: Float,
	val paddingD: Int = 0,
	val destination: Vector3<Long>,
	val state: State,
	val paddingE: Byte = 0,
	val paddingF: Short = 0,
	val paddingG: Int = 0
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		id.writeTo(writer)
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
				id = Id(reader.readLong()),
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

	@JvmInline
	value class Id(val value: Long) : CwSerializable {
		override suspend fun writeTo(writer: Writer) {
			writer.writeLong(value)
		}


		companion object : CwDeserializer<Id> {
			override suspend fun readFrom(reader: Reader) = Id(reader.readLong())
		}
	}

	enum class State : CwSerializableEnumByte {
		;//TODO: figure out which airship states exist

		companion object : CwEnumByteDeserializer<State> {
			override val values = values()
		}
	}
}