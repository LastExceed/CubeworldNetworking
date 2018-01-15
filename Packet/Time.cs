using System.IO;

namespace Resources.Packet {
    public class Time : Packet {
        public int day;
        public int time;

        public Time() : base() {
            PacketID = PacketID.time;
        }
        public Time(BinaryReader reader) : this() {
            day = reader.ReadInt32();
            time = reader.ReadInt32();
        }

        protected override void WritePacketData(BinaryWriter writer, bool writePacketID = true) {
            writer.Write(day);
            writer.Write(time);
        }
    }
}
