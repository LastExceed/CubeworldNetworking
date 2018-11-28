using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace Resources.Packet {
    class MultiEntityUpdate : Packet {
        public List<EntityUpdate> entityUpdates = new List<EntityUpdate>();

        public MultiEntityUpdate() : base(PacketID.MultiEntityUpdate) { }
        public MultiEntityUpdate(BinaryReader reader) : base(reader) {
            var count = reader.ReadInt32();
            for (int i = 0; i < count; i++) {
                entityUpdates.Add(new EntityUpdate(reader));
            }
        }

        protected override void WritePacketData(BinaryWriter writer) {
            writer.Write(entityUpdates.Count);
            foreach (var entityUpdate in entityUpdates) {
                entityUpdate.Write(writer);
            }
        }
    }
}
