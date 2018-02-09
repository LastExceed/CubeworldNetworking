using System.IO;

namespace Resources.Packet {
    public class ServerFull : Packet {

        public ServerFull() : base() {
            PacketID = PacketID.Time;
        }
        public ServerFull(BinaryReader reader) : this() {
        }

        protected override void WritePacketData(BinaryWriter writer) { }
    }
}
