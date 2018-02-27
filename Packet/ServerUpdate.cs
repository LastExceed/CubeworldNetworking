using System.Collections.Generic;
using System.IO;
using Resources.Utilities;

namespace Resources.Packet {
    public class ServerUpdate : Packet {
        public class BlockDelta {
            public IntVector position;
            public ByteVector color;
            public BlockType type;
            public int unknown;

            public BlockDelta() { }
            public BlockDelta(BinaryReader reader) {
                position = new IntVector(reader);
                color = new ByteVector(reader);
                type = (BlockType)reader.ReadByte();
                unknown = reader.ReadInt32();
            }

            public void Write(BinaryWriter writer) {
                position.Write(writer);
                color.Write(writer);
                writer.Write((byte)type);
                writer.Write(unknown);
            }
        }
        public class Particle {
            public LongVector position;
            public FloatVector velocity;
            public FloatVector color;
            public float alpha;
            public float size;
            public int count;
            public ParticleType type;
            public float spread;
            public int unknown;

            public Particle() { }
            public Particle(BinaryReader reader) {
                position = new LongVector(reader);
                velocity = new FloatVector(reader);
                color = new FloatVector(reader);
                alpha = reader.ReadSingle();
                size = reader.ReadSingle();
                count = reader.ReadInt32();
                type = (ParticleType)reader.ReadInt32();
                spread = reader.ReadSingle();
                unknown = reader.ReadInt32();
            }

            public void Write(BinaryWriter writer) {
                position.Write(writer);
                velocity.Write(writer);
                color.Write(writer);
                writer.Write(alpha);
                writer.Write(size);
                writer.Write(count);
                writer.Write((int)type);
                writer.Write(spread);
                writer.Write(unknown);
            }
        }
        public class Sound {
            public IntVector position;
            public SoundID soundID;
            public float pitch;
            public float volume;

            public Sound() { }
            public Sound(BinaryReader reader) {
                position = new IntVector(reader);
                soundID = (SoundID)reader.ReadInt32();
                pitch = reader.ReadSingle();
                volume = reader.ReadSingle();
            }

            public void Write(BinaryWriter writer) {
                position.Write(writer);
                writer.Write((int)soundID);
                writer.Write(pitch);
                writer.Write(volume);
            }
        }
        public class StaticEntity {
            public int chunkX;
            public int chunkY;
            public int id;
            public int paddingA;
            public StaticEntityType type;
            public int paddingB;
            public LongVector position;
            public int rotation;
            public FloatVector size;
            public bool closed; //bool?
            //3pad
            public int time; //for open/close doors ect
            public int unknown;
            public int paddingC;
            public long guid; //of player who interacts with it

            public StaticEntity() { }
            public StaticEntity(BinaryReader reader) {
                chunkX = reader.ReadInt32();
                chunkY = reader.ReadInt32();
                id = reader.ReadInt32();
                paddingA = reader.ReadInt32();
                type = (StaticEntityType)reader.ReadInt32();
                paddingB = reader.ReadInt32();
                position = new LongVector(reader);
                rotation = reader.ReadInt32();
                size = new FloatVector(reader);
                closed = reader.ReadBoolean();
                reader.ReadBytes(3);
                time = reader.ReadInt32();
                unknown = reader.ReadInt32();
                paddingC = reader.ReadInt32();
                guid = reader.ReadInt64();
            }

            public void Write(BinaryWriter writer) {
                writer.Write(chunkX);
                writer.Write(chunkY);
                writer.Write(id);
                writer.Write(paddingA);
                writer.Write((int)type);
                writer.Write(paddingB);
                position.Write(writer);
                writer.Write(rotation);
                size.Write(writer);
                writer.Write(closed);
                writer.Write(new byte[3]);
                writer.Write(time);
                writer.Write(unknown);
                writer.Write(paddingC);
                writer.Write(guid);
            }
        }
        public class ChunkItems {
            public class DroppedItem {
                public Item item;
                public long posX;
                public long posY;
                public long posZ;
                public float rotation;
                public float scale;
                public int unknownA; //bool?
                public int droptime;
                public int unknownB;
                public int unknownC;

                public DroppedItem() { }
                public DroppedItem(BinaryReader reader) {
                    item = new Item(reader);
                    posX = reader.ReadInt64();
                    posY = reader.ReadInt64();
                    posZ = reader.ReadInt64();
                    rotation = reader.ReadSingle();
                    scale = reader.ReadSingle();
                    unknownA = reader.ReadInt32();
                    droptime = reader.ReadInt32();
                    unknownB = reader.ReadInt32();
                    unknownC = reader.ReadInt32();
                }

                public void Write(BinaryWriter writer) {
                    item.Write(writer);
                    writer.Write(posX);
                    writer.Write(posY);
                    writer.Write(posZ);
                    writer.Write(rotation);
                    writer.Write(scale);
                    writer.Write(unknownA);
                    writer.Write(droptime);
                    writer.Write(unknownB);
                    writer.Write(unknownC);
                }
            }

            public int chunkX;
            public int chunkY;
            public List<DroppedItem> droppedItems = new List<DroppedItem>();

            public ChunkItems() { }
            public ChunkItems(BinaryReader reader) {
                chunkX = reader.ReadInt32();
                chunkY = reader.ReadInt32();
                int m = reader.ReadInt32();
                for (int i = 0; i < m; i++) {
                    droppedItems.Add(new DroppedItem(reader));
                }
            }

            public void Write(BinaryWriter writer) {
                writer.Write(chunkX);
                writer.Write(chunkY);
                writer.Write(droppedItems.Count);
                foreach (DroppedItem droppedItem in droppedItems) {
                    droppedItem.Write(writer);
                }
            }
        }
        public class P48 {
            public class SubP48 {
                public ulong unknownA;
                public ulong unknownB;

                public SubP48() { }
                public SubP48(BinaryReader reader) {
                    unknownA = reader.ReadUInt64();
                    unknownB = reader.ReadUInt64();
                }

                public void Write(BinaryWriter writer) {
                    writer.Write(unknownA);
                    writer.Write(unknownB);
                }
            }

            public ulong unknown;
            public List<SubP48> subP48s = new List<SubP48>();

            public P48() { }
            public P48(BinaryReader reader) {
                unknown = reader.ReadUInt64();
                int count = reader.ReadInt32();
                for (int i = 0; i < count; i++) {
                    subP48s.Add(new SubP48(reader));
                }
            }

            public void Write(BinaryWriter writer) {
                writer.Write(unknown);
                writer.Write(subP48s.Count);
                foreach (SubP48 subP48 in subP48s) {
                    subP48.Write(writer);
                }
            }
        }
        public class Pickup {
            public long guid;
            public Item item;

            public Pickup() { }
            public Pickup(BinaryReader reader) {
                guid = reader.ReadInt64();
                item = new Item(reader);
            }

            public void Write(BinaryWriter writer) {
                writer.Write(guid);
                item.Write(writer);
            }
        }
        public class Kill {
            public long killer;
            public long victim;
            public int unknown;
            public int xp;

            public Kill() { }
            public Kill(BinaryReader reader) {
                killer = reader.ReadInt64();
                victim = reader.ReadInt64();
                unknown = reader.ReadInt32();
                xp = reader.ReadInt32();
            }

            public void Write(BinaryWriter writer) {
                writer.Write(killer);
                writer.Write(victim);
                writer.Write(unknown);
                writer.Write(xp);
            }
        }
        public class Damage {
            public ulong target;
            public ulong attacker;
            public float damage;
            public int unknown;

            public Damage() { }
            public Damage(BinaryReader reader) {
                target = reader.ReadUInt64();
                attacker = reader.ReadUInt64();
                damage = reader.ReadSingle();
                unknown = reader.ReadInt32();
            }

            public void Write(BinaryWriter writer) {
                writer.Write(target);
                writer.Write(attacker);
                writer.Write(damage);
                writer.Write(unknown);
            }
        }
        public class Mission {
            public int sectionX;
            public int sectionY;
            public int unknownA;
            public int unknownB;
            public int unknownC;
            public int id;
            public int type;
            public EntityType monsterID;
            public int level;
            public byte unknownE;
            public MissionState state; //0=ready 1=progressing 2=finished
            public short padding;
            public int currentHP;
            public int maxHP;
            public int chunkX;
            public int chunkY;

            public Mission() { }
            public Mission(BinaryReader reader) {
                sectionX = reader.ReadInt32();
                sectionY = reader.ReadInt32();
                unknownA = reader.ReadInt32();
                unknownB = reader.ReadInt32();
                unknownC = reader.ReadInt32();
                id = reader.ReadInt32();
                type = reader.ReadInt32();
                monsterID = (EntityType)reader.ReadInt32();
                level = reader.ReadInt32();
                unknownE = reader.ReadByte();
                state = (MissionState)reader.ReadByte();
                padding = reader.ReadInt16();
                currentHP = reader.ReadInt32();
                maxHP = reader.ReadInt32();
                chunkX = reader.ReadInt32();
                chunkY = reader.ReadInt32();
            }

            public void Write(BinaryWriter writer) {
                writer.Write(sectionX);
                writer.Write(sectionY);
                writer.Write(unknownA);
                writer.Write(unknownB);
                writer.Write(unknownC);
                writer.Write(id);
                writer.Write(type);
                writer.Write((int)monsterID);
                writer.Write(level);
                writer.Write(unknownE);
                writer.Write((byte)state);
                writer.Write(padding);
                writer.Write(currentHP);
                writer.Write(maxHP);
                writer.Write(chunkX);
                writer.Write(chunkY);
            }
        }

        public List<BlockDelta> blockDeltas = new List<BlockDelta>();
        public List<Hit> hits = new List<Hit>();
        public List<Particle> particles = new List<Particle>();
        public List<Sound> sounds = new List<Sound>();
        public List<Shoot> shoots = new List<Shoot>();
        public List<StaticEntity> statics = new List<StaticEntity>();
        public List<ChunkItems> chunkItems = new List<ChunkItems>();
        public List<P48> p48s = new List<P48>();
        public List<Pickup> pickups = new List<Pickup>();
        public List<Kill> kills = new List<Kill>();
        public List<Damage> damages = new List<Damage>();
        public List<PassiveProc> passiveProcs = new List<PassiveProc>();
        public List<Mission> missions = new List<Mission>();

        public ServerUpdate() : base() {
            PacketID = PacketID.ServerUpdate;
        }
        public ServerUpdate(BinaryReader reader) : this() {
            byte[] uncompressed = Zlib.Decompress(reader.ReadBytes(reader.ReadInt32()));
            MemoryStream stream = new MemoryStream(uncompressed);
            BinaryReader r = new BinaryReader(stream);

            var count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                blockDeltas.Add(new BlockDelta(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                hits.Add(new Hit(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                particles.Add(new Particle(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                sounds.Add(new Sound(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                shoots.Add(new Shoot(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                statics.Add(new StaticEntity(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                chunkItems.Add(new ChunkItems(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                p48s.Add(new P48(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                pickups.Add(new Pickup(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                kills.Add(new Kill(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                damages.Add(new Damage(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                passiveProcs.Add(new PassiveProc(r));
            }

            count = r.ReadInt32();
            for (int i = 0; i < count; i++) {
                missions.Add(new Mission(r));
            }
        }

        protected override void WritePacketData(BinaryWriter writer) {
            MemoryStream stream = new MemoryStream();
            BinaryWriter _wr = new BinaryWriter(stream);

            _wr.Write(blockDeltas.Count);
            foreach (BlockDelta blockDelta in blockDeltas) {
                blockDelta.Write(_wr);
            }

            _wr.Write(hits.Count);
            foreach (Hit hit in hits) {
                hit.Write(_wr, false);
            }

            _wr.Write(particles.Count);
            foreach (Particle particle in particles) {
                particle.Write(_wr);
            }

            _wr.Write(sounds.Count);
            foreach (Sound sound in sounds) {
                sound.Write(_wr);
            }

            _wr.Write(shoots.Count);
            foreach (Shoot shoot in shoots) {
                shoot.Write(_wr, false);
            }

            _wr.Write(statics.Count);
            foreach (StaticEntity staticEntity in statics) {
                staticEntity.Write(_wr);
            }

            _wr.Write(chunkItems.Count);
            foreach (ChunkItems chunkItem in chunkItems) {
                chunkItem.Write(_wr);
            }

            _wr.Write(p48s.Count);
            foreach (P48 p48 in p48s) {
                p48.Write(_wr);
            }

            _wr.Write(pickups.Count);
            foreach (Pickup pickup in pickups) {
                pickup.Write(_wr);
            }

            _wr.Write(kills.Count);
            foreach (Kill kill in kills) {
                kill.Write(_wr);
            }

            _wr.Write(damages.Count);
            foreach (Damage damage in damages) {
                damage.Write(_wr);
            }

            _wr.Write(passiveProcs.Count); //npc rClick ??? todo
            foreach (PassiveProc passiveProc in passiveProcs) {
                passiveProc.Write(_wr, false);
            }

            _wr.Write(missions.Count);
            foreach (Mission mission in missions) {
                mission.Write(_wr);
            }

            byte[] data = Zlib.Compress(stream.ToArray());
            stream = new MemoryStream();
            _wr = new BinaryWriter(stream);
            _wr.Write(data.Length);
            _wr.Write(data);

            writer.Write(stream.ToArray());
        }
    }
}
