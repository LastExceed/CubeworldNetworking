﻿using System.IO;
using System.Text;
using Resources.Utilities;
using System;

namespace Resources.Packet {
    public class EntityUpdate : Packet{
        public class Appearance {
            public byte unknownA;
            public byte unknownB;
            public ByteVector hair_color;
            public short flags;
            public byte unknownC; //padding?
            public FloatVector character_size;
            public short head_model;
            public short hair_model;
            public short hand_model;
            public short foot_model;
            public short body_model;
            public short tail_model;
            public short shoulder2_model;
            public short wings_model;
            public float head_size;
            public float body_size;
            public float hand_size;
            public float foot_size;
            public float shoulder2_size;
            public float weapon_size;
            public float tail_size;
            public float shoulder_size;
            public float wings_size;
            public float body_rotation;
            public FloatVector hand_rotation;
            public float feet_rotation;
            public float wing_rotation;
            public float tail_rotation;
            public FloatVector body_offset;
            public FloatVector head_offset;
            public FloatVector hand_offset;
            public FloatVector foot_offset;
            public FloatVector back_offset;
            public FloatVector wing_offset;

            public Appearance() {
                hair_color = new ByteVector();
                character_size = new FloatVector();
                hand_rotation = new FloatVector();
                body_offset = new FloatVector();
                head_offset = new FloatVector();
                hand_offset = new FloatVector();
                foot_offset = new FloatVector();
                back_offset = new FloatVector();
                wing_offset = new FloatVector();
            }
            public Appearance(BinaryReader reader) {
                unknownA = reader.ReadByte();
                unknownB = reader.ReadByte();
                hair_color = new ByteVector(reader);
                flags = reader.ReadInt16();
                unknownC = reader.ReadByte();
                character_size = new FloatVector(reader);
                head_model = reader.ReadInt16();
                hair_model = reader.ReadInt16();
                hand_model = reader.ReadInt16();
                foot_model = reader.ReadInt16();
                body_model = reader.ReadInt16();
                tail_model = reader.ReadInt16();
                shoulder2_model = reader.ReadInt16();
                wings_model = reader.ReadInt16();
                head_size = reader.ReadSingle();
                body_size = reader.ReadSingle();
                hand_size = reader.ReadSingle();
                foot_size = reader.ReadSingle();
                shoulder2_size = reader.ReadSingle();
                weapon_size = reader.ReadSingle();
                tail_size = reader.ReadSingle();
                shoulder_size = reader.ReadSingle();
                wings_size = reader.ReadSingle();
                body_rotation = reader.ReadSingle();
                hand_rotation = new FloatVector(reader);
                feet_rotation = reader.ReadSingle();
                wing_rotation = reader.ReadSingle();
                tail_rotation = reader.ReadSingle();
                body_offset = new FloatVector(reader);
                head_offset = new FloatVector(reader);
                hand_offset = new FloatVector(reader);
                foot_offset = new FloatVector(reader);
                back_offset = new FloatVector(reader);
                wing_offset = new FloatVector(reader);

            }

            public void Write(BinaryWriter writer) {
                writer.Write(unknownA);
                writer.Write(unknownB);
                hair_color.Write(writer);
                writer.Write(flags);
                writer.Write(unknownC);
                character_size.Write(writer);
                writer.Write(head_model);
                writer.Write(hair_model);
                writer.Write(hand_model);
                writer.Write(foot_model);
                writer.Write(body_model);
                writer.Write(tail_model);
                writer.Write(shoulder2_model);
                writer.Write(wings_model);
                writer.Write(head_size);
                writer.Write(body_size);
                writer.Write(hand_size);
                writer.Write(foot_size);
                writer.Write(shoulder2_size);
                writer.Write(weapon_size);
                writer.Write(tail_size);
                writer.Write(shoulder_size);
                writer.Write(wings_size);
                writer.Write(body_rotation);
                hand_rotation.Write(writer);
                writer.Write(feet_rotation);
                writer.Write(wing_rotation);
                writer.Write(tail_rotation);
                body_offset.Write(writer);
                head_offset.Write(writer);
                hand_offset.Write(writer);
                foot_offset.Write(writer);
                back_offset.Write(writer);
                wing_offset.Write(writer);
            }
        }
        public class Multipliers {
            public float HP;
            public float attackSpeed;
            public float damage;
            public float armor;
            public float resi;

            public void Write(BinaryWriter writer) {
                writer.Write(HP);
                writer.Write(attackSpeed);
                writer.Write(damage);
                writer.Write(armor);
                writer.Write(resi);
            }

            public Multipliers() { }
            public Multipliers(BinaryReader reader) {
                HP = reader.ReadSingle();
                attackSpeed = reader.ReadSingle();
                damage = reader.ReadSingle();
                armor = reader.ReadSingle();
                resi = reader.ReadSingle();
            }
        }
        public class SkillDistribution {
            public int petmaster;
            public int petriding;
            public int sailing;
            public int climbing;
            public int hangGliding;
            public int swimming;
            public int ability1;
            public int ability2;
            public int ability3;
            public int ability4;
            public int ability5;

            public void Write(BinaryWriter writer) {
                writer.Write(petmaster);
                writer.Write(petriding);
                writer.Write(sailing);
                writer.Write(climbing);
                writer.Write(hangGliding);
                writer.Write(swimming);
                writer.Write(ability1);
                writer.Write(ability2);
                writer.Write(ability3);
                writer.Write(ability4);
                writer.Write(ability5);
            }

            public SkillDistribution() { }
            public SkillDistribution(BinaryReader reader) {
                petmaster = reader.ReadInt32();
                petriding = reader.ReadInt32();
                sailing = reader.ReadInt32();
                climbing = reader.ReadInt32();
                hangGliding = reader.ReadInt32();
                swimming = reader.ReadInt32();
                ability1 = reader.ReadInt32();
                ability2 = reader.ReadInt32();
                ability3 = reader.ReadInt32();
                ability4 = reader.ReadInt32();
                ability5 = reader.ReadInt32();
            }
        }

        public long guid;
        public LongVector position;
        public FloatVector rotation;
        public FloatVector velocity;
        public FloatVector acceleration;
        public FloatVector extraVel;
        public float? viewportPitch;
        public int? physicsFlags;
        public Hostility? hostility;
        public EntityType? entityType;
        public Mode? mode;
        public int? modeTimer;
        public int? combo;
        public int? lastHitTime;
        public Appearance appearance;
        public short? entityFlags;
        public int? roll;
        public int? stun;
        public int? slow;
        public int? ice;
        public int? wind;
        public int? showPatchTime;
        public EntityClass? entityClass;
        public byte? specialization;
        public float? charge;
        public FloatVector unused24;
        public FloatVector unused25;
        public FloatVector rayHit;
        public float? HP;
        public float? MP;
        public float? block;
        public Multipliers multipliers;
        public byte? unused31;
        public byte? unused32;
        public int? level;
        public int? XP;
        public long? parentOwner;
        public long? unused36;
        public byte? powerBase;
        public int? unused38;
        public IntVector unused39;
        public LongVector spawnPos;
        public IntVector unused41;
        public byte? unused42;
        public Item consumable;
        public Item[] equipment;
        public string name;
        public SkillDistribution skillDistribution;
        public int? manaCubes;
        internal byte[] Data {
            get {
                long bitfield = 0;

                var stream = new MemoryStream();
                var writer = new BinaryWriter(stream);

                if (position != null) {
                    position.Write(writer);
                    Tools.SetBit(ref bitfield, true, 0);
                }
                if (rotation != null) {
                    rotation.Write(writer);
                    Tools.SetBit(ref bitfield, true, 1);
                }
                if (velocity != null) {
                    velocity.Write(writer);
                    Tools.SetBit(ref bitfield, true, 2);
                }
                if (acceleration != null) {
                    acceleration.Write(writer);
                    Tools.SetBit(ref bitfield, true, 3);
                }
                if (extraVel != null) {
                    extraVel.Write(writer);
                    Tools.SetBit(ref bitfield, true, 4);
                }
                if (viewportPitch != null) {
                    writer.Write((float)viewportPitch);
                    Tools.SetBit(ref bitfield, true, 5);
                }
                if (physicsFlags != null) {
                    writer.Write((int)physicsFlags);
                    Tools.SetBit(ref bitfield, true, 6);
                }
                if (hostility != null) {
                    writer.Write((byte)hostility);
                    Tools.SetBit(ref bitfield, true, 7);
                }
                if (entityType != null) {
                    writer.Write((int)entityType);
                    Tools.SetBit(ref bitfield, true, 8);
                }
                if (mode != null) {
                    writer.Write((byte)mode);
                    Tools.SetBit(ref bitfield, true, 9);
                }
                if (modeTimer != null) {
                    writer.Write((int)modeTimer);
                    Tools.SetBit(ref bitfield, true, 10);
                }
                if (combo != null) {
                    writer.Write((int)combo);
                    Tools.SetBit(ref bitfield, true, 11);
                }
                if (lastHitTime != null) {
                    writer.Write((int)lastHitTime);
                    Tools.SetBit(ref bitfield, true, 12);
                }
                if (appearance != null) {
                    appearance.Write(writer);
                    Tools.SetBit(ref bitfield, true, 13);
                }
                if (entityFlags != null) {
                    writer.Write((short)entityFlags);
                    Tools.SetBit(ref bitfield, true, 14);
                }
                if (roll != null) {
                    writer.Write((int)roll);
                    Tools.SetBit(ref bitfield, true, 15);
                }
                if (stun != null) {
                    writer.Write((int)stun);
                    Tools.SetBit(ref bitfield, true, 16);
                }
                if (slow != null) {
                    writer.Write((int)slow);
                    Tools.SetBit(ref bitfield, true, 17);
                }
                if (ice != null) {
                    writer.Write((int)ice);
                    Tools.SetBit(ref bitfield, true, 18);
                }
                if (wind != null) {
                    writer.Write((int)wind);
                    Tools.SetBit(ref bitfield, true, 19);
                }
                if (showPatchTime != null) {
                    writer.Write((int)showPatchTime);
                    Tools.SetBit(ref bitfield, true, 20);
                }
                if (entityClass != null) {
                    writer.Write((byte)entityClass);
                    Tools.SetBit(ref bitfield, true, 21);
                }
                if (specialization != null) {
                    writer.Write((byte)specialization);
                    Tools.SetBit(ref bitfield, true, 22);
                }
                if (charge != null) {
                    writer.Write((float)charge);
                    Tools.SetBit(ref bitfield, true, 23);
                }
                if (unused24 != null) {
                    unused24.Write(writer);
                    Tools.SetBit(ref bitfield, true, 24);
                }
                if (unused25 != null) {
                    unused25.Write(writer);
                    Tools.SetBit(ref bitfield, true, 25);
                }
                if (rayHit != null) {
                    rayHit.Write(writer);
                    Tools.SetBit(ref bitfield, true, 26);
                }
                if (HP != null) {
                    writer.Write((float)HP);
                    Tools.SetBit(ref bitfield, true, 27);
                }
                if (MP != null) {
                    writer.Write((float)MP);
                    Tools.SetBit(ref bitfield, true, 28);
                }
                if (block != null) {
                    writer.Write((float)block);
                    Tools.SetBit(ref bitfield, true, 29);
                }
                if (multipliers != null) {
                    multipliers.Write(writer);
                    Tools.SetBit(ref bitfield, true, 30);
                }
                if (unused31 != null) {
                    writer.Write((byte)unused31);
                    Tools.SetBit(ref bitfield, true, 31);
                }
                if (unused32 != null) {
                    writer.Write((byte)unused32);
                    Tools.SetBit(ref bitfield, true, 32);
                }
                if (level != null) {
                    writer.Write((int)level);
                    Tools.SetBit(ref bitfield, true, 33);
                }
                if (XP != null) {
                    writer.Write((int)XP);
                    Tools.SetBit(ref bitfield, true, 34);
                }
                if (parentOwner != null) {
                    writer.Write((long)parentOwner);
                    Tools.SetBit(ref bitfield, true, 35);
                }
                if (unused36 != null) {
                    writer.Write((long)unused36);
                    Tools.SetBit(ref bitfield, true, 36);
                }
                if (powerBase != null) {
                    writer.Write((byte)powerBase);
                    Tools.SetBit(ref bitfield, true, 37);
                }
                if (unused38 != null) {
                    writer.Write((int)unused38);
                    Tools.SetBit(ref bitfield, true, 38);
                }
                if (unused39 != null) {
                    unused39.Write(writer);
                    Tools.SetBit(ref bitfield, true, 39);
                }
                if (spawnPos != null) {
                    spawnPos.Write(writer);
                    Tools.SetBit(ref bitfield, true, 40);
                }
                if (unused41 != null) {
                    unused41.Write(writer);
                    Tools.SetBit(ref bitfield, true, 41);
                }
                if (unused42 != null) {
                    writer.Write((byte)unused42);
                    Tools.SetBit(ref bitfield, true, 42);
                }
                if (consumable != null) {
                    consumable.Write(writer);
                    Tools.SetBit(ref bitfield, true, 43);
                }
                if (equipment != null) {
                    foreach (var item in equipment) {
                        item.Write(writer);
                    }
                    Tools.SetBit(ref bitfield, true, 44);
                }
                if (name != null) {
                    byte[] nameBytes = Encoding.ASCII.GetBytes(name);
                    writer.Write(nameBytes);
                    writer.Write(new byte[16 - nameBytes.Length]);
                    Tools.SetBit(ref bitfield, true, 45);
                }
                if (skillDistribution != null) {
                    skillDistribution.Write(writer);
                    Tools.SetBit(ref bitfield, true, 46);
                }
                if (manaCubes != null) {
                    writer.Write((int)manaCubes);
                    Tools.SetBit(ref bitfield, true, 47);
                }

                var data = stream.ToArray();
                stream = new MemoryStream();
                writer = new BinaryWriter(stream);
                writer.Write(guid);
                writer.Write(bitfield);
                writer.Write(data);

                byte[] compressed = Zlib.Compress(stream.ToArray());

                stream = new MemoryStream();
                writer = new BinaryWriter(stream);
                writer.Write(compressed.Length);
                writer.Write(compressed);
                return stream.ToArray();
            }
        }
        public bool IsEmpty {
        get {
                return !(position != null ||
                   rotation != null ||
                   velocity != null ||
                   acceleration != null ||
                   extraVel != null ||
                   viewportPitch != null ||
                   physicsFlags != null ||
                   hostility != null ||
                   entityType != null ||
                   mode != null ||
                   modeTimer != null ||
                   combo != null ||
                   lastHitTime != null ||
                   appearance != null ||
                   entityFlags != null ||
                   roll != null ||
                   stun != null ||
                   slow != null ||
                   ice != null ||
                   wind != null ||
                   showPatchTime != null ||
                   entityClass != null ||
                   specialization != null ||
                   charge != null ||
                   unused24 != null ||
                   unused25 != null ||
                   rayHit != null ||
                   HP != null ||
                   MP != null ||
                   block != null ||
                   multipliers != null ||
                   unused31 != null ||
                   unused32 != null ||
                   level != null ||
                   XP != null ||
                   parentOwner != null ||
                   unused36 != null ||
                   powerBase != null ||
                   unused38 != null ||
                   unused39 != null ||
                   spawnPos != null ||
                   unused41 != null ||
                   unused42 != null ||
                   consumable != null ||
                   equipment != null ||
                   name != null ||
                   skillDistribution != null ||
                   manaCubes != null);
            }
        }

        public EntityUpdate() : base(){
            PacketID = PacketID.EntityUpdate;
        }
        public EntityUpdate(BinaryReader reader) : base(reader){
            byte[] uncompressed = Zlib.Decompress(reader.ReadBytes(reader.ReadInt32()));
            MemoryStream stream = new MemoryStream(uncompressed);
            BinaryReader r = new BinaryReader(stream);
            guid = r.ReadInt64();
            var bitfield = r.ReadInt64();

            if(Tools.GetBit(bitfield, 0)) {
                position = new LongVector(r);
            }
            if(Tools.GetBit(bitfield, 1)) {
                rotation = new FloatVector(r);
            }
            if(Tools.GetBit(bitfield, 2)) {
                velocity = new FloatVector(r);
            }
            if(Tools.GetBit(bitfield, 3)) {
                acceleration = new FloatVector(r);
            }
            if(Tools.GetBit(bitfield, 4)) {
                extraVel = new FloatVector(r);
            }
            if(Tools.GetBit(bitfield, 5)) {
                viewportPitch = r.ReadSingle();
            }
            if(Tools.GetBit(bitfield, 6)) {
                physicsFlags = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 7)) {
                hostility = (Hostility)r.ReadByte();
            }
            if(Tools.GetBit(bitfield, 8)) {
                entityType = (EntityType)r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 9)) {
                mode = (Mode)r.ReadByte();
            }
            if(Tools.GetBit(bitfield, 10)) {
                modeTimer = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 11)) {
                combo = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 12)) {
                lastHitTime = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 13)) {
                appearance = new Appearance(r);
            }
            if(Tools.GetBit(bitfield, 14)) {
                entityFlags = r.ReadInt16();
            }
            if(Tools.GetBit(bitfield, 15)) {
                roll = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 16)) {
                stun = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 17)) {
                slow = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 18)) {
                ice = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 19)) {
                wind = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 20)) {
                showPatchTime = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 21)) {
                entityClass = (EntityClass)r.ReadByte();
            }
            if(Tools.GetBit(bitfield, 22)) {
                specialization = r.ReadByte();
            }
            if(Tools.GetBit(bitfield, 23)) {
                charge = r.ReadSingle();
            }
            if(Tools.GetBit(bitfield, 24)) {
                unused24 = new FloatVector(r);
            }
            if(Tools.GetBit(bitfield, 25)) {
                unused25 = new FloatVector(r);
            }
            if(Tools.GetBit(bitfield, 26)) {
                rayHit = new FloatVector(r);
            }
            if(Tools.GetBit(bitfield, 27)) {
                HP = r.ReadSingle();
            }
            if(Tools.GetBit(bitfield, 28)) {
                MP = r.ReadSingle();
            }
            if(Tools.GetBit(bitfield, 29)) {
                block = r.ReadSingle();
            }
            if(Tools.GetBit(bitfield, 30)) {
                multipliers = new Multipliers(r);
            }
            if(Tools.GetBit(bitfield, 31)) {
                unused31 = r.ReadByte();
            }
            if(Tools.GetBit(bitfield, 32)) {
                unused32 = r.ReadByte();
            }
            if(Tools.GetBit(bitfield, 33)) {
                level = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 34)) {
                XP = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 35)) {
                parentOwner = r.ReadInt64();
            }
            if(Tools.GetBit(bitfield, 36)) {
                unused36 = r.ReadInt64();
            }
            if(Tools.GetBit(bitfield, 37)) {
                powerBase = r.ReadByte();
            }
            if(Tools.GetBit(bitfield, 38)) {
                unused38 = r.ReadInt32();
            }
            if(Tools.GetBit(bitfield, 39)) {
                unused39 = new IntVector(r);
            }
            if(Tools.GetBit(bitfield, 40)) {
                spawnPos = new LongVector(r);
            }
            if(Tools.GetBit(bitfield, 41)) {
                unused41 = new IntVector(r);
            }
            if(Tools.GetBit(bitfield, 42)) {
                unused42 = r.ReadByte();
            }
            if(Tools.GetBit(bitfield, 43)) {
                consumable = new Item(r);
            }
            if(Tools.GetBit(bitfield, 44)) {
                equipment = new Item[13];
                for(int i = 0; i < 13; i++) {
                    equipment[i] = new Item(r);
                }
            }
            if(Tools.GetBit(bitfield, 45)) {
                name = new string(r.ReadChars(16));
                name = name.Substring(0, name.IndexOf("\0"));
            }
            if(Tools.GetBit(bitfield, 46)) {
                skillDistribution = new SkillDistribution(r);
            }
            if(Tools.GetBit(bitfield, 47)) {
                manaCubes = r.ReadInt32();
            }
        }
        public EntityUpdate(byte[] data) : this(Convert(data)) { }
        private static BinaryReader Convert(byte[] data) {
            var reader = new BinaryReader(new MemoryStream(data));
            reader.ReadByte();
            return reader;
        }

        protected override void WritePacketData(BinaryWriter writer) {
            writer.Write(Data);
        }

        public void Merge(EntityUpdate playerEntityData) {
            if(position != null) {
                playerEntityData.position = position;
            }
            if(rotation != null) {
                playerEntityData.rotation = rotation;
            }
            if(velocity != null) {
                playerEntityData.velocity = velocity;
            }
            if(acceleration != null) {
                playerEntityData.acceleration = acceleration;
            }
            if(extraVel != null) {
                playerEntityData.extraVel = extraVel;
            }
            if(viewportPitch != null) {
                playerEntityData.viewportPitch = viewportPitch;
            }
            if(physicsFlags != null) {
                playerEntityData.physicsFlags = physicsFlags;
            }
            if(hostility != null) {
                playerEntityData.hostility = hostility;
            }
            if(entityType != null) {
                playerEntityData.entityType = entityType;
            }
            if(mode != null) {
                playerEntityData.mode = mode;
            }
            if(modeTimer != null) {
                playerEntityData.modeTimer = modeTimer;
            }
            if(combo != null) {
                playerEntityData.combo = combo;
            }
            if(lastHitTime != null) {
                playerEntityData.lastHitTime = lastHitTime;
            }
            if(appearance != null) {
                playerEntityData.appearance = appearance;
            }
            if(entityFlags != null) {
                playerEntityData.entityFlags = entityFlags;
            }
            if(roll != null) {
                playerEntityData.roll = roll;
            }
            if(stun != null) {
                playerEntityData.stun = stun;
            }
            if(slow != null) {
                playerEntityData.slow = slow;
            }
            if(ice != null) {
                playerEntityData.ice = ice;
            }
            if(wind != null) {
                playerEntityData.wind = wind;
            }
            if(showPatchTime != null) {
                playerEntityData.showPatchTime = showPatchTime;
            }
            if(entityClass != null) {
                playerEntityData.entityClass = entityClass;
            }
            if(specialization != null) {
                playerEntityData.specialization = specialization;
            }
            if(charge != null) {
                playerEntityData.charge = charge;
            }
            if(unused24 != null) {
                playerEntityData.unused24 = unused24;
            }
            if(unused25 != null) {
                playerEntityData.unused25 = unused25;
            }
            if(rayHit != null) {
                playerEntityData.rayHit = rayHit;
            }
            if(HP != null) {
                playerEntityData.HP = HP;
            }
            if(MP != null) {
                playerEntityData.MP = MP;
            }
            if(block != null) {
                playerEntityData.block = block;
            }
            if(multipliers != null) {
                playerEntityData.multipliers = multipliers;
            }
            if(unused31 != null) {
                playerEntityData.unused31 = unused31;
            }
            if(unused32 != null) {
                playerEntityData.unused32 = unused32;
            }
            if(level != null) {
                playerEntityData.level = level;
            }
            if(XP != null) {
                playerEntityData.XP = XP;
            }
            if(parentOwner != null) {
                playerEntityData.parentOwner = parentOwner;
            }
            if(unused36 != null) {
                playerEntityData.unused36 = unused36;
            }
            if(powerBase != null) {
                playerEntityData.powerBase = powerBase;
            }
            if(unused38 != null) {
                playerEntityData.unused38 = unused38;
            }
            if(unused39 != null) {
                playerEntityData.unused39 = unused39;
            }
            if(spawnPos != null) {
                playerEntityData.spawnPos = spawnPos;
            }
            if(unused41 != null) {
                playerEntityData.unused41 = unused41;
            }
            if(unused42 != null) {
                playerEntityData.unused42 = unused42;
            }
            if(consumable != null) {
                playerEntityData.consumable = consumable;
            }
            if(equipment != null) {
                playerEntityData.equipment = equipment;
            }
            if(name != null) {
                playerEntityData.name = name;
            }
            if(skillDistribution != null) {
                playerEntityData.skillDistribution = skillDistribution;
            }
            if(manaCubes != null) {
                playerEntityData.manaCubes = manaCubes;
            }
        }
        public void Filter(EntityUpdate previous) {
            if(position != null) {
                if (Math.Abs(position.x - previous.position.x) < 100000 &&
                    Math.Abs(position.y - previous.position.y) < 100000 &&
                    Math.Abs(position.z - previous.position.z) < 100000) {
                    position = null;
                }
            }
            rotation = null;
            if(velocity != null) {
                if(Math.Abs(velocity.z - previous.velocity.z) < 2) {
                    velocity = null;
                }
            }
            if (acceleration != null) {
                if (Math.Abs(acceleration.x - previous.acceleration.x) < 10 &&
                    Math.Abs(acceleration.y - previous.acceleration.y) < 10 &&
                    Math.Abs(acceleration.z - previous.acceleration.z) < 10) {
                    acceleration = null;
                }
            }
            if (extraVel != null) {
                if(Math.Abs(extraVel.x) < 1 &&
                    Math.Abs(extraVel.y) < 1 &&
                    Math.Abs(extraVel.z) < 1) {
                    extraVel = null;
                }
            }
            viewportPitch = null;
            physicsFlags = null;
            if(modeTimer != null && modeTimer > 100) {
                previous.modeTimer = modeTimer;//necessary for rayhit filtering
                modeTimer = null;
            }
            lastHitTime = null;
            if(roll != null && !(roll > 500)) {
                roll = null;
            }
            if(stun != null && stun < previous.stun) {
                stun = null;
            }
            if(slow != null && slow < previous.slow) {
                slow = null;
            }
            if(ice != null && ice < previous.ice) {
                ice = null;
            }
            if(wind != null && wind < previous.wind) {
                wind = null;
            }
            showPatchTime = null;
            if (charge != null) {
                if (Math.Abs((float)(charge - previous.charge)) < 0.1f) {
                    charge = null;
                }
            }
            unused24 = null;
            unused25 = null;
            if(rayHit != null) {
                if(previous.mode == 0 || previous.modeTimer > 1500 || //current could be null
                    (Math.Abs(rayHit.x - previous.rayHit.x) < 1.5f &&
                     Math.Abs(rayHit.y - previous.rayHit.y) < 1.5f &&
                     Math.Abs(rayHit.z - previous.rayHit.z) < 1.5f)) {
                    rayHit = null;
                }
            }
            MP = null;
            multipliers = null;
            unused31 = null;
            unused32 = null;
            XP = null;
            unused36 = null;
            powerBase = null;
            unused38 = null;
            unused39 = null;
            spawnPos = null;
            unused41 = null;
            unused42 = null;
            skillDistribution = null;
            manaCubes = null;
        }
    }
}