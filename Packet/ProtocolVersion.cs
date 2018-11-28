using System.IO;

namespace Resources.Packet {
    public class ProtocolVersion : Packet {
        public int version;

        public ProtocolVersion() : base(PacketID.Version) { }
        public ProtocolVersion(BinaryReader reader) : this() {
            version = reader.ReadInt32();
        }

        protected override void WritePacketData(BinaryWriter writer) {
            writer.Write(version);
        }
    }
}
