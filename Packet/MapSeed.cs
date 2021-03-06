﻿using System.IO;

namespace Resources.Packet {
    public class MapSeed : Packet{
        public int seed;

        public MapSeed() : base(PacketID.MapSeed) { }
        public MapSeed(BinaryReader reader) : this() {
            seed = reader.ReadInt32();
        }

        protected override void WritePacketData(BinaryWriter writer) {
            writer.Write(seed);
        }
    }
}
