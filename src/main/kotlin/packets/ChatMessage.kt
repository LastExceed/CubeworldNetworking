package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

data class ChatMessage(
	val sender: CreatureId?,
	val text: String
) : Packet(PacketId.ChatMessage) {
	override suspend fun writeTo(writer: Writer) {
		if (sender != null) {
			writer.writeLong(sender.value)
		}
		writer.writeInt(text.length)
		writer.writeByteArray(text.toByteArray(Charsets.UTF_16LE))
	}

	companion object { //TODO: CwDeserializer<ChatMessage>
		suspend fun readFrom(reader: Reader, readSender: Boolean) =
			ChatMessage(
				sender = if (readSender) CreatureId(reader.readLong()) else null,
				text = String(reader.readByteArray(reader.readInt() * 2), Charsets.UTF_16LE)
			)
	}
}