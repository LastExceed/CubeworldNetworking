using System.IO;

namespace Resources.Packet {
    public abstract class Packet {
        public readonly PacketID packetID;

        public Packet(PacketID packetID) => this.packetID = packetID;
        public Packet(BinaryReader reader) { }

        protected abstract void WritePacketData(BinaryWriter writer);

        public void Write(BinaryWriter writer, bool writePacketID = true) {
            if (writePacketID) writer.Write((int)packetID);
            this.WritePacketData(writer);
        }
    }
}
