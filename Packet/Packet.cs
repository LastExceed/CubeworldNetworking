using System.Collections.Generic;
using System.IO;
using System.Threading;

namespace Resources.Packet {
    public abstract class Packet {
        public PacketID PacketID { get; internal set; }

        public Packet() { }
        public Packet(BinaryReader reader) { }

        protected abstract void WritePacketData(BinaryWriter writer, bool writePacketID = true);

        public void Write(BinaryWriter writer, bool writePacketID = true) {
            if (writePacketID) writer.Write((int)PacketID);
            this.WritePacketData(writer);
        }
        public void Broadcast(Dictionary<long, Player> players, long toSkip) {
            foreach (Player player in new List<Player>(players.Values)) {
                if (player.entityData.guid != toSkip) {
                    SpinWait.SpinUntil(() => player.available);
                    player.available = false;
                    try {
                        this.Write(player.writer);
                    }
                    catch (IOException) { }
                    player.available = true;
                }
            }
        }
    }
}
