using System.Collections.Generic;
using System.IO;
using System.Threading;

namespace Resources.Packet {
    public class EntityUpdatesFinished : Packet {

        public EntityUpdatesFinished() : base() {
            PacketID = PacketID.entityUpdatesFinished;
        }

        public EntityUpdatesFinished(BinaryReader reader) : this() { }

        protected override void WritePacketData(BinaryWriter writer, bool writePacketID = true) { }
    }
}
