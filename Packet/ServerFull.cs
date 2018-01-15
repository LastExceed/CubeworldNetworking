using System.IO;

namespace Resources.Packet {
    public class ServerFull : Packet {

        public ServerFull() : base() {
            PacketID = PacketID.time;
        }

        public ServerFull(BinaryReader reader) : this() {
        }

        protected override void WritePacketData(BinaryWriter writer, bool writePacketID = true) { }
    }
}
