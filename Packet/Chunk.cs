using System.IO;

namespace Resources.Packet {
    public class Chunk : Packet {
        public int chunkX;
        public int chunkY;

        public Chunk() : base(PacketID.Chunk) { }
        public Chunk(BinaryReader reader) : base(reader){
            chunkX = reader.ReadInt32();
            chunkY = reader.ReadInt32();
        }

        protected override void WritePacketData(BinaryWriter writer) {
            writer.Write(chunkX);
            writer.Write(chunkY);
        }
    }
}
