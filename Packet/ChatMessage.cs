using System.IO;
using System.Text;

namespace Resources.Packet {
    public class ChatMessage : Packet {
        public long? sender;
        public string message;

        public ChatMessage() : base() {
            PacketID = PacketID.chat;
        }
        public ChatMessage(BinaryReader reader, bool readSender = false) : base(reader) {
            if (readSender) sender = reader.ReadInt64();
            message = Encoding.Unicode.GetString(reader.ReadBytes(reader.ReadInt32() * 2));
        }

        protected override void WritePacketData(BinaryWriter writer) {
            byte[] mBytes = Encoding.Unicode.GetBytes(message);
            if (sender != null) {
                writer.Write((long)sender);
            }
            writer.Write(mBytes.Length / 2);
            writer.Write(mBytes);
        }
    }
}
