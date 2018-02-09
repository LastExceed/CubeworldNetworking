using System.IO;

namespace Resources.Packet {
    public class PassiveProc : Packet {
        public long source;
        public long target;
        public ProcType type;
        //3pad
        public float modifier;
        public int duration;
        public int unknown;
        public long guid3;

        public PassiveProc() : base() {
            PacketID = PacketID.PassiveProc;
        }
        public PassiveProc(BinaryReader reader) : this() {
            source = reader.ReadInt64();
            target = reader.ReadInt64();
            type = (ProcType)reader.ReadByte();
            reader.ReadBytes(3);//pad
            modifier = reader.ReadSingle();
            duration = reader.ReadInt32();
            unknown = reader.ReadInt32();
            guid3 = reader.ReadInt64();
        }

        protected override void WritePacketData(BinaryWriter writer) {
            writer.Write(source);
            writer.Write(target);
            writer.Write((byte)type);
            writer.Write(new byte[3]);
            writer.Write(modifier);
            writer.Write(duration);
            writer.Write(unknown);
            writer.Write(guid3);
        }
    }
}
