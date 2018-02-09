﻿using Resources.Utilities;
using System.IO;

namespace Resources.Packet {
    public class Shoot : Packet {
        public long attacker;
        public int chunkX;
        public int chunkY;
        public int unknownA;
        public int paddingA;
        public LongVector position;
        public IntVector unknownV;
        public FloatVector velocity;
        public float legacyDMG;
        public float unknownB;
        public float scale;
        public float mana;
        public float particles;
        public int skill;
        public ProjectileType projectile;
        public int paddingB;
        public float unknownC;
        public float unknownD;

        public Shoot() : base() {
            PacketID = PacketID.Shoot;
            position = new LongVector();
            unknownV = new IntVector();
            velocity = new FloatVector();
        }
        public Shoot(BinaryReader reader) : this() {
            attacker = reader.ReadInt64();
            chunkX = reader.ReadInt32();
            chunkY = reader.ReadInt32();
            unknownA = reader.ReadInt32();
            paddingA = reader.ReadInt32();
            position = new LongVector(reader);
            unknownV = new IntVector(reader);
            velocity = new FloatVector(reader);
            legacyDMG = reader.ReadSingle();
            unknownB = reader.ReadSingle();
            scale = reader.ReadSingle();
            mana = reader.ReadSingle();
            particles = reader.ReadSingle();
            skill = reader.ReadInt32();
            projectile = (ProjectileType)reader.ReadInt32();
            paddingB = reader.ReadInt32();
            unknownC = reader.ReadSingle();
            unknownD = reader.ReadSingle();
        }

        protected override void WritePacketData(BinaryWriter writer) {
            writer.Write(attacker);
            writer.Write(chunkX);
            writer.Write(chunkY);
            writer.Write(unknownA);
            writer.Write(paddingA);
            position.Write(writer);
            unknownV.Write(writer);
            velocity.Write(writer);
            writer.Write(legacyDMG);
            writer.Write(unknownB);
            writer.Write(scale);
            writer.Write(mana);
            writer.Write(particles);
            writer.Write(skill);
            writer.Write((int)projectile);
            writer.Write(paddingB);
            writer.Write(unknownC);
            writer.Write(unknownD);
        }
    }
}
