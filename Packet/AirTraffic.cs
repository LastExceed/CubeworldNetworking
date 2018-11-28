using Resources.Utilities;
using System.Collections.Generic;
using System.IO;

namespace Resources.Packet {
    public class AirTraffic : Packet {
        public class Airship {
            public long id;
            public int unk2;//bool
            public int unk3;
            public LongVector position;
            public FloatVector velocity = new FloatVector();
            public float rotation;
            public LongVector station;
            public float pathRotation;
            public int unk4;
            public LongVector destination;
            public int flightStage;//bool
            public int unk6;//bool

            public Airship() { }
            internal Airship(BinaryReader reader) {
                reader.ReadBytes(120);
                //TODO
            }
            internal void Write(BinaryWriter writer) {
                writer.Write(id);
                writer.Write(unk2);
                writer.Write(unk3);
                position.Write(writer);
                velocity.Write(writer);
                writer.Write(rotation);
                station.Write(writer);
                writer.Write(pathRotation);
                writer.Write(unk4);
                destination.Write(writer);
                writer.Write(flightStage);
                writer.Write(unk6);
                //var dat = new byte[] { 0x00, 0x23, 0x80, 0x23, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xD0, 0x68, 0x1C, 0x21, 0x92, 0xED, 0x16, 0x24, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0xC5, 0x23, 0x80, 0x00, 0x00, 0x00, 0xD2, 0xF8, 0xC6, 0x00, 0x00, 0x00, 0x00, 0x00, 0x66, 0x16, 0x15, 0xC1, 0x00, 0x00, 0x00, 0x00, 0xAE, 0x4E, 0x68, 0xC0, 0x64, 0x00, 0x87, 0x43, 0x00, 0x00, 0xE5, 0x23, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0xC5, 0x23, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0xB3, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x87, 0x43, 0xF0, 0x8D, 0x29, 0x22, 0x00, 0x00, 0x17, 0x24, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0xF7, 0x23, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0xC7, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x9F, 0x0A, 0x4D, 0x00, 0x00, 0x00, 0x00 };
                //writer.Write(dat);
            }
        }

        public List<Airship> airships = new List<Airship>();

        public AirTraffic() : base() {
            PacketID = PacketID.AirTraffic;
        }
        public AirTraffic(BinaryReader reader) : base(reader) {
            var count = reader.ReadInt32();
            for (int i = 0; i < count; i++) {
                airships.Add(new Airship(reader));
            }
        }

        protected override void WritePacketData(BinaryWriter writer) {
            writer.Write(airships.Count);
            foreach (var airship in airships) {
                airship.Write(writer);
            }
        }
    }
}
