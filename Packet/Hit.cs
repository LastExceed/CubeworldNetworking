using System.IO;
using Resources.Utilities;

namespace Resources.Packet {
    public class Hit : Packet {
        public long attacker;
        public long target;
        public float damage;
        public bool critical;//bool?
        //3pad
        public int stuntime;
        public int paddingA;
        public LongVector position = new LongVector();
        public FloatVector direction = new FloatVector();
        public byte isYellow;
        public DamageType type;
        public bool showlight;
        public byte paddingB;

        public Hit() : base() {
            PacketID = PacketID.hit;
        }
        public Hit(BinaryReader reader) : this() {
            attacker = reader.ReadInt64();
            target = reader.ReadInt64();
            damage = reader.ReadSingle();
            critical = reader.ReadBoolean();
            reader.ReadBytes(3);
            stuntime = reader.ReadInt32();
            paddingA = reader.ReadInt32();
            position = new LongVector(reader);
            direction = new FloatVector(reader);
            isYellow = reader.ReadByte();
            type = (DamageType)reader.ReadByte();
            showlight = reader.ReadBoolean();
            paddingB = reader.ReadByte();
        }

        protected override void WritePacketData(BinaryWriter writer) {
            writer.Write(attacker);
            writer.Write(target);
            writer.Write(damage);
            writer.Write(critical);
            writer.Write(new byte[3]);
            writer.Write(stuntime);
            writer.Write(paddingA);
            position.Write(writer);
            direction.Write(writer);
            writer.Write(isYellow);
            writer.Write((byte)type);
            writer.Write(showlight);
            writer.Write(paddingB);
        }
    }
}
