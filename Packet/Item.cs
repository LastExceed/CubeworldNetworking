using System.Collections.Generic;
using System.IO;

namespace Resources.Packet {
    public class Item {
        public ItemType type;
        public byte subtype;
        //2 pad
        public int modifier;
        public int unknown;
        public ItemRarity rarity;
        public ItemMaterial material;
        public bool adapted; //bool?
        //1pad
        public short level;
        //2 pad
        Spirit[] spirits = new Spirit[32];
        public int spiritCounter;

        public Item() {
            for (int i = 0; i < 32; i++) {
                spirits[i] = new Spirit();
            }
        }
        public Item(BinaryReader reader) {
            type = (ItemType)reader.ReadByte();
            subtype = reader.ReadByte();
            reader.ReadBytes(2);
            modifier = reader.ReadInt32();
            unknown = reader.ReadInt32();
            rarity = (ItemRarity)reader.ReadByte();
            material = (ItemMaterial)reader.ReadByte();
            adapted = reader.ReadBoolean();
            reader.ReadBytes(1);
            level = reader.ReadInt16();
            reader.ReadBytes(2);

            for (int i = 0; i < 32; i++) {
                spirits[i] = new Spirit(reader);
            }
            spiritCounter = reader.ReadInt32();
        }

        public void Write(BinaryWriter writer) {
            writer.Write((byte)type);
            writer.Write(subtype);
            writer.Write(new byte[2]);
            writer.Write(modifier);
            writer.Write(unknown);
            writer.Write((byte)rarity);
            writer.Write((byte)material);
            writer.Write(adapted);
            writer.Write((byte)0);
            writer.Write(level);
            writer.Write(new byte[2]);
            foreach (Spirit spirit in spirits) {
                spirit.Write(writer);
            }
            writer.Write(spiritCounter);
        }
    }

    public class Spirit {
        public byte x;
        public byte y;
        public byte z;
        public ItemMaterial material;
        public short level;
        //2 pad

        public Spirit() { }

        public Spirit(BinaryReader reader) {
            x = reader.ReadByte();
            y = reader.ReadByte();
            z = reader.ReadByte();
            material = (ItemMaterial)reader.ReadByte();
            level = reader.ReadInt16();
            reader.ReadBytes(2);
        }

        public void Write(BinaryWriter writer) {
            writer.Write(x);
            writer.Write(y);
            writer.Write(z);
            writer.Write((byte)material);
            writer.Write(level);
            writer.Write(new byte[2]);
        }
    }
}
