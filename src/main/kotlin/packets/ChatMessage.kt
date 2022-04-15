package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*

abstract class ChatMessage : Packet(PacketId.ChatMessage) {
	abstract val text: String

	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(text.length)
		writer.writeByteArray(text.toByteArray(Charsets.UTF_16LE))
	}

	data class FromClient(
		override val text: String
	) : ChatMessage() {
		companion object : CwDeserializer<ChatMessage.FromClient> {
			override suspend fun readFrom(reader: Reader) =
				ChatMessage.FromClient(
					text = readText(reader)
				)
		}
	}

	data class FromServer(
		val source: CreatureId,
		override val text: String
	) : ChatMessage() {
		override suspend fun writeTo(writer: Writer) {
			source.writeTo(writer)
			super.writeTo(writer)
		}

		companion object : CwDeserializer<ChatMessage.FromServer> {
			override suspend fun readFrom(reader: Reader) =
				ChatMessage.FromServer(
					source = CreatureId.readFrom(reader),
					text = readText(reader)
				)
		}
	}

	companion object {
		private suspend fun readText(reader: Reader) =
			String(reader.readByteArray(reader.readInt() * 2), Charsets.UTF_16LE)

		/** The Cubeworld protocol is inconsistent here (blame Wollay).
		 * When sent from client to server, this packet is missing one of its properties (namely [ChatMessage.FromServer.source]).
		 * In order to stay compliant with [CwDeserializer], the packet was split into 2 subclasses:
		 * @see [ChatMessage.FromClient.readFrom]
		 * @see [ChatMessage.FromServer.readFrom] */
		@Deprecated(
			"See KDoc",
			level = DeprecationLevel.ERROR,
			replaceWith = ReplaceWith("ChatMessage.FromClient.readFrom(reader)")
		)
		fun readFrom(reader: Reader, readSender: Boolean): Nothing = error("unreachable")
	}
}