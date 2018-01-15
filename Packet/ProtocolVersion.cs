using System.IO;

namespace Resources.Packet {
    public class ProtocolVersion : Packet {
        public int version;

        public ProtocolVersion() : base() {
            PacketID = PacketID.version;
        }

        public ProtocolVersion(BinaryReader reader) : this() {
            version = reader.ReadInt32();
        }

        protected override void WritePacketData(BinaryWriter writer, bool writePacketID = true) {
            writer.Write(version);
        }
    }
}
