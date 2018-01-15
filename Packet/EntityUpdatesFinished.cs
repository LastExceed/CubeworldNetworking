using System.IO;

namespace Resources.Packet {
    public class EntityUpdatesFinished : Packet {

        public EntityUpdatesFinished() : base() {
            PacketID = PacketID.entityUpdatesFinished;
        }
        public EntityUpdatesFinished(BinaryReader reader) : this() { }

        protected override void WritePacketData(BinaryWriter writer, bool writePacketID = true) { }
    }
}
