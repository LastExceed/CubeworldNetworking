﻿using System.IO;

namespace Resources.Packet {
    public class EntityAction {
        public const int packetID = 6;

        public Item item;
        public int chunkX;
        public int chunkY;
        public int index;
        public int unknown;
        public ActionType type;
        //3 pad

        public EntityAction() { }

        public EntityAction(BinaryReader reader) {
            item = new Item(reader);
            chunkX = reader.ReadInt32();
            chunkY = reader.ReadInt32();
            index = reader.ReadInt32();
            unknown = reader.ReadInt32();
            type = (ActionType)reader.ReadByte();
            reader.ReadBytes(3); //pad
        }

        public void Write(BinaryWriter writer, bool writePacketID = true) {
            if(writePacketID) {
                writer.Write(packetID);
            }
            item.Write(writer);
            writer.Write(chunkX);
            writer.Write(chunkY);
            writer.Write(index);
            writer.Write(unknown);
            writer.Write((byte)type);
            writer.Write(new byte[3]);
        }
    }
}
