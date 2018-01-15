using System.IO;

namespace Resources.Packet {
    public class Sector : Packet{
        public int sectorX;
        public int sectorY;

        public Sector() : base() {
            PacketID = PacketID.sector;
        }
        public Sector(BinaryReader reader) : this() {
            sectorX = reader.ReadInt32();
            sectorY = reader.ReadInt32();
        }

        protected override void WritePacketData(BinaryWriter writer, bool writePacketID = true) {
            writer.Write(sectorX);
            writer.Write(sectorY);
        }
    }
}
