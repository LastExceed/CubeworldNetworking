package me.lastexceed.cubeworldnetworking.packets

import me.lastexceed.cubeworldnetworking.utils.*

class Join(
	val unknown: Int,
	val assignedID: CreatureID,
	val junk: ByteArray
) : Packet(PacketId.Join) {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(unknown)
		writer.writeLong(assignedID.value)
		writer.writeByteArray(junk)
	}

	companion object : CwDeserializer<Join> {
		override suspend fun readFrom(reader: Reader) =
			Join(
				unknown = reader.readInt(),
				assignedID = CreatureID(reader.readLong()),
				junk = reader.readByteArray(0x1168)
			)
	}
}