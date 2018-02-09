using System.IO;

namespace Resources.Packet {
    public class EntityUpdatesFinished : Packet {

        public EntityUpdatesFinished() : base() {
            PacketID = PacketID.EntityUpdatesFinished;
        }
        public EntityUpdatesFinished(BinaryReader reader) : this() { }

        protected override void WritePacketData(BinaryWriter writer) { }
    }
}
