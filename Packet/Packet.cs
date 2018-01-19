using System.IO;

namespace Resources.Packet {
    public abstract class Packet {
        public PacketID PacketID { get; internal set; }

        public Packet() { }
        public Packet(BinaryReader reader) { }

        protected abstract void WritePacketData(BinaryWriter writer);

        public void Write(BinaryWriter writer, bool writePacketID = true) {
            if (writePacketID) writer.Write((int)PacketID);
            this.WritePacketData(writer);
        }
    }
}
