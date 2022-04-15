package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

class PlayerInitialization(
	val unknown: Int = 0,
	val assignedId: CreatureId,
	val borkedCreatureData: ByteArray = ByteArray(0x1168)
) : Packet(PacketId.PlayerInitialization) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(unknown)
		assignedId.writeTo(writer)
		writer.writeByteArray(borkedCreatureData)
	}

	companion object : CwDeserializer<PlayerInitialization> {
		override suspend fun readFrom(reader: Reader) =
			PlayerInitialization(
				unknown = reader.readInt(),
				assignedId = CreatureId.readFrom(reader),
				borkedCreatureData = reader.readByteArray(0x1168)
			)
	}
}