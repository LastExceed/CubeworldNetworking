package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class CreatureAction(
	val item: Item,
	val chunk: Vector2<Int>,
	val itemIndex: Int,
	val unknownA: Int = 0,
	val type: Type,
	val unknownB: Byte = 0,
	val unknownC: Short = 0
) : Packet(PacketId.CreatureAction) {
	override suspend fun writeTo(writer: Writer) {
		item.writeTo(writer)
		writer.writeVector2Int(chunk)
		writer.writeInt(itemIndex)
		writer.writeInt(unknownA)
		type.writeTo(writer)
		writer.writeByte(unknownB)
		writer.writeShort(unknownC)
	}

	companion object : CwDeserializer<CreatureAction> {
		override suspend fun readFrom(reader: Reader) =
			CreatureAction(
				item = Item.readFrom(reader),
				chunk = reader.readVector2Int(),
				itemIndex = reader.readInt(),
				unknownA = reader.readInt(),
				type = Type.readFrom(reader),
				unknownB = reader.readByte(),
				unknownC = reader.readShort()
			)
	}

	enum class Type : CwSerializableEnumByte {
		Zero,
		Bomb,
		Talk,
		ObjectInteraction,
		Unknown4,
		PickUp,
		Drop,
		Unknown7,
		CallPet;

		companion object : CwEnumByteDeserializer<Type> {
			override val values = values()
		}
	}
}