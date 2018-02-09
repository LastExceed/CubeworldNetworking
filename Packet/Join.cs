using System.IO;

namespace Resources.Packet {
    public class Join : Packet {
        public int unknown;
        public long guid;
        public byte[] junk;

        public Join() : base() {
            PacketID = PacketID.JoinPacket;
        }
        public Join(BinaryReader reader) : this() {
            unknown = reader.ReadInt32();
            guid = reader.ReadInt64();
            junk = reader.ReadBytes(0x1168);
        }

        protected override void WritePacketData(BinaryWriter writer) {
            writer.Write(unknown);
            writer.Write(guid);
            writer.Write(junk);
        }
    }
}
