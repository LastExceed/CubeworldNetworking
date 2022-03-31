package com.github.lastexceed.cubeworldnetworking.packets

import io.ktor.util.toByteArray
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.close
import com.github.lastexceed.cubeworldnetworking.utils.*

data class ServerUpdate(
	val worldEdits: List<WorldEdit> = emptyList(),
	val hits: List<Hit> = emptyList(),
	val particles: List<Particle> = emptyList(),
	val sounds: List<Sound> = emptyList(),
	val shots: List<Shot> = emptyList(),
	val worldObjects: List<WorldObject> = emptyList(),
	val chunkLoots: List<ChunkLoot> = emptyList(),
	val p48s: List<P48> = emptyList(),
	val pickups: List<Pickup> = emptyList(),
	val kills: List<Kill> = emptyList(),
	val attacks: List<Attack> = emptyList(),
	val statusEffects: List<StatusEffect> = emptyList(),
	val missions: List<Mission> = emptyList()
) : Packet(PacketId.ServerUpdate) {
	override suspend fun writeTo(writer: Writer) {
		val inflatedChannel = ByteChannel(true)
		val inflatedWriter = Writer(inflatedChannel)

		listOf(
			worldEdits,
			hits,
			particles,
			sounds,
			shots,
			worldObjects,
			chunkLoots,
			p48s,
			pickups,
			kills,
			attacks,
			statusEffects,
			missions
		).forEach {
			inflatedWriter.writeInt(it.size)
			it.forEach { subPacket ->
				subPacket.writeTo(inflatedWriter)
			}
		}
		inflatedChannel.close()
		val deflated = Zlib.deflate(inflatedChannel.toByteArray())
		writer.writeInt(deflated.size)
		writer.writeByteArray(deflated)
	}

	companion object : CwDeserializer<ServerUpdate> {
		override suspend fun readFrom(reader: Reader) =
			Reader(Zlib.inflate(reader.readByteArray(reader.readInt()))).run {
				ServerUpdate(
					worldEdits = List(readInt()) { WorldEdit.readFrom(this) },
					hits = List(readInt()) { Hit.readFrom(this) },
					particles = List(readInt()) { Particle.readFrom(this) },
					sounds = List(readInt()) { Sound.readFrom(this) },
					shots = List(readInt()) { Shot.readFrom(this) },
					worldObjects = List(readInt()) { WorldObject.readFrom(this) },
					chunkLoots = List(readInt()) { ChunkLoot.readFrom(this) },
					p48s = List(readInt()) { P48.readFrom(this) },
					pickups = List(readInt()) { Pickup.readFrom(this) },
					kills = List(readInt()) { Kill.readFrom(this) },
					attacks = List(readInt()) { Attack.readFrom(this) },
					statusEffects = List(readInt()) { StatusEffect.readFrom(this) },
					missions = List(readInt()) { Mission.readFrom(this) }
				)
			}
	}
}

data class WorldEdit(
	val position: Vector3<Int>,
	val color: Vector3<Byte>,
	val blockType: BlockType,
	val unknown: Int
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeVector3Int(position)
		writer.writeVector3Byte(color)
		blockType.writeTo(writer)
		writer.writeInt(unknown)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			WorldEdit(
				position = reader.readVector3Int(),
				color = reader.readVector3Byte(),
				blockType = BlockType.readFrom(reader),
				unknown = reader.readInt()
			)
	}

	enum class BlockType : CwSerializableEnumByte {
		Air,
		Solid,
		Liquid,
		Wet;

		companion object : CwEnumByteDeserializer<BlockType> {
			override val values = values()
		}
	}
}

data class Particle(
	val position: Vector3<Long>,
	val velocity: Vector3<Float>,
	val color: Vector3<Float>,
	val alpha: Float,
	val size: Float,
	val count: Int,
	val type: Type,
	val spread: Float,
	val unknown: Int
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeVector3Long(position)
		writer.writeVector3Float(velocity)
		writer.writeVector3Float(color)
		writer.writeFloat(alpha)
		writer.writeFloat(size)
		writer.writeInt(count)
		type.writeTo(writer)
		writer.writeFloat(spread)
		writer.writeInt(unknown)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Particle(
				position = reader.readVector3Long(),
				velocity = reader.readVector3Float(),
				color = reader.readVector3Float(),
				alpha = reader.readFloat(),
				size = reader.readFloat(),
				count = reader.readInt(),
				type = Type.readFrom(reader),
				spread = reader.readFloat(),
				unknown = reader.readInt()
			)
	}

	enum class Type : CwSerializableEnumInt {
		Normal,
		Spark,
		Unknown,
		NoSpreadNoRotation,
		NoGravity;

		companion object : CwEnumIntDeserializer<Type> {
			override val values = values()
		}
	}
}

data class Sound(
	val position: Vector3<Float>,
	val type: Type,
	val pitch: Float = 1f,
	val volume: Float = 1f
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeVector3Float(position)
		type.writeTo(writer)
		writer.writeFloat(pitch)
		writer.writeFloat(volume)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Sound(
				position = reader.readVector3Float(),
				type = Type.readFrom(reader),
				pitch = reader.readFloat(),
				volume = reader.readFloat()
			)
	}

	enum class Type : CwSerializableEnumInt {
		Hit,
		Blade1,
		Blade2,
		LongBlade1,
		LongBlade2,
		Hit1,
		Hit2,
		Punch1,
		Punch2,
		HitArrow,
		HitArrowCritical,
		Smash1,
		SlamGround,
		SmashHit2,
		SmashJump,
		Swing,
		ShieldSwing,
		SwingSlow,
		SwingSlow2,
		ArrowDestroy,
		Blade3,
		Punch3,
		Salvo2,
		SwordHit03,
		Block,
		ShieldSlam,
		Roll,
		Destroy2,
		Cry,
		Levelup2,
		Missioncomplete,
		Watersplash01,
		Step2,
		StepWater,
		StepWater2,
		StepWater3,
		Channel2,
		ChannelHit,
		Fireball,
		FireHit,
		Magic01,
		Watersplash,
		WatersplashHit,
		LichScream,
		Drink2,
		Pickup,
		Disenchant2,
		Upgrade2,
		Swirl,
		HumanVoice01,
		HumanVoice02,
		Gate,
		SpikeTrap,
		FireTrap,
		Lever,
		Charge2,
		Magic02,
		Drop,
		DropCoin,
		DropItem,
		MaleGroan,
		FemaleGroan,
		MaleGroan2,
		FemaleGroan2,
		GoblinMaleGroan,
		GoblinFemaleGroan,
		LizardMaleGroan,
		LizardFemaleGroan,
		DwarfMaleGroan,
		DwarfFemaleGroan,
		OrcMaleGroan,
		OrcFemaleGroan,
		UndeadMaleGroan,
		UndeadFemaleGroan,
		FrogmanMaleGroan,
		FrogmanFemaleGroan,
		MonsterGroan,
		TrollGroan,
		MoleGroan,
		SlimeGroan,
		ZombieGroan,
		Explosion,
		Punch4,
		MenuOpen2,
		MenuClose2,
		MenuSelect,
		MenuTab,
		MenuGrabItem,
		MenuDropItem,
		Craft,
		CraftProc,
		Absorb,
		Manashield,
		Bulwark,
		Bird1,
		Bird2,
		Bird3,
		Cricket1,
		Cricket2,
		Owl1,
		Owl2;

		companion object : CwEnumIntDeserializer<Type> {
			override val values = values()
		}
	}
}

data class WorldObject(
	val chunk: Vector2<Int>,
	val objectID: Int,
	val paddingA: Int,
	val objectType: ObjectType,
	val paddingB: Int,
	val position: Vector3<Long>,
	val orientation: Orientation,
	val size: Vector3<Float>,
	val isClosed: Boolean,
	val transformTime: Int,
	val unknown: Int,
	val paddingC: Int,
	val interactor: Long
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeVector2Int(chunk)
		writer.writeInt(objectID)
		writer.writeInt(paddingA)
		objectType.writeTo(writer)
		writer.writeInt(paddingB)
		writer.writeVector3Long(position)
		orientation.writeTo(writer)
		writer.writeVector3Float(size)
		writer.writeBoolean(isClosed); writer.pad(3)
		writer.writeInt(transformTime)
		writer.writeInt(unknown)
		writer.writeInt(paddingC)
		writer.writeLong(interactor)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			WorldObject(
				chunk = reader.readVector2Int(),
				objectID = reader.readInt(),
				paddingA = reader.readInt(),
				objectType = ObjectType.readFrom(reader),
				paddingB = reader.readInt(),
				position = reader.readVector3Long(),
				orientation = Orientation.readFrom(reader),
				size = reader.readVector3Float(),
				isClosed = reader.readInt() > 0,
				transformTime = reader.readInt(),
				unknown = reader.readInt(),
				paddingC = reader.readInt(),
				interactor = reader.readLong()
			)
	}

	enum class ObjectType : CwSerializableEnumInt {
		Statue,
		Door,
		BigDoor,
		Window,
		CastleWindow,
		Gate,
		FireTrap,
		SpikeTrap,
		StompTrap,
		Lever,
		Chest,
		ChestTop02,
		Table1,
		Table2,
		Table3,
		Stool1,
		Stool2,
		Stool3,
		Bench,
		Bed,
		BedTable,
		MarketStand1,
		MarketStand2,
		MarketStand3,
		Barrel,
		Crate,
		OpenCrate,
		Sack,
		Shelter,
		Cupboard,
		Desktop,
		Counter,
		Shelf1,
		Shelf2,
		Shelf3,
		CastleShelf1,
		CastleShelf2,
		CastleShelf3,
		StoneShelf1,
		StoneShelf2,
		StoneShelf3,
		SandstoneShelf1,
		SandstoneShelf2,
		SandstoneShelf3,
		Corpse,
		RuneStone,
		Artifact,
		FlowerBox1,
		FlowerBox2,
		FlowerBox3,
		StreetLight,
		FireStreetLight,
		Fence1,
		Fence2,
		Fence3,
		Fence4,
		Vase1,
		Vase2,
		Vase3,
		Vase4,
		Vase5,
		Vase6,
		Vase7,
		Vase8,
		Vase9,
		Campfire,
		Tent,
		BeachUmbrella,
		BeachTowel,
		SleepingMat,
		Furnace,
		Anvil,
		SpinningWheel,
		Loom,
		SawBench,
		Workbench,
		CustomizationBench;

		companion object : CwEnumIntDeserializer<ObjectType> {
			override val values = values()
		}
	}

	enum class Orientation : CwSerializableEnumInt {
		South,
		East,
		North,
		West;

		companion object : CwEnumIntDeserializer<Orientation> {
			override val values = values()
		}
	}
}

data class ChunkLoot(
	val chunk: Vector2<Int>,
	val drops: List<Drop> = listOf()
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeVector2Int(chunk)
		writer.writeInt(drops.size)
		drops.forEach { it.writeTo(writer) }
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			ChunkLoot(
				chunk = reader.readVector2Int(),
				drops = List(reader.readInt()) { Drop.readFrom(reader) }
			)
	}
}

data class Drop(
	val item: Item,
	val position: Vector3<Long>,
	val rotation: Float,
	val scale: Float,
	val unknownA: Int,
	val droptime: Int,
	val unknownB: Int,
	val unknownC: Int
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		item.writeTo(writer)
		writer.writeVector3Long(position)
		writer.writeFloat(rotation)
		writer.writeFloat(scale)
		writer.writeInt(unknownA)
		writer.writeInt(droptime)
		writer.writeInt(unknownB)
		writer.writeInt(unknownC)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Drop(
				item = Item.readFrom(reader),
				position = reader.readVector3Long(),
				rotation = reader.readFloat(),
				scale = reader.readFloat(),
				unknownA = reader.readInt(),
				droptime = reader.readInt(),
				unknownB = reader.readInt(),
				unknownC = reader.readInt()
			)
	}
}

data class P48(
	val chunk: Vector2<Int>,
	val subPackets: List<ByteArray> = listOf()
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeVector2Int(chunk)
		writer.writeInt(subPackets.size)
		subPackets.forEach {
			writer.writeByteArray(it)
		}
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			P48(
				chunk = reader.readVector2Int(),
				subPackets = List(reader.readInt()) { reader.readByteArray(16) }
			)
	}
}

data class Pickup(
	val interactor: CreatureId,
	val item: Item
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(interactor.value)
		item.writeTo(writer)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Pickup(
				interactor = CreatureId(reader.readLong()),
				item = Item.readFrom(reader)
			)
	}
}

data class Kill(
	val killer: CreatureId,
	val victim: CreatureId,
	val unknown: Int,
	val xp: Int
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(killer.value)
		writer.writeLong(victim.value)
		writer.writeInt(unknown)
		writer.writeInt(xp)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Kill(
				killer = CreatureId(reader.readLong()),
				victim = CreatureId(reader.readLong()),
				unknown = reader.readInt(),
				xp = reader.readInt()
			)
	}
}

data class Attack(
	val target: Long,
	val attacker: Long,
	val damage: Float,
	val unknown: Int
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeLong(target)
		writer.writeLong(attacker)
		writer.writeFloat(damage)
		writer.writeInt(unknown)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Attack(
				target = reader.readLong(),
				attacker = reader.readLong(),
				damage = reader.readFloat(),
				unknown = reader.readInt()
			)
	}
}

data class Mission(
	val sector: Vector2<Int>,
	val unknownA: Int,
	val unknownB: Int,
	val unknownC: Int,
	val id: Int,
	val type: Int,
	val boss: Race,
	val level: Int,
	val unknownE: Byte,
	val state: State,
	val padding: Short,
	val currentHP: Int,
	val maxHP: Int,
	val chunk: Vector2<Int>
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeVector2Int(sector)
		writer.writeInt(unknownA)
		writer.writeInt(unknownB)
		writer.writeInt(unknownC)
		writer.writeInt(id)
		writer.writeInt(type)
		boss.writeTo(writer)
		writer.writeInt(level)
		writer.writeByte(unknownE)
		state.writeTo(writer)
		writer.writeShort(padding)
		writer.writeInt(currentHP)
		writer.writeInt(maxHP)
		writer.writeVector2Int(chunk)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Mission(
				sector = reader.readVector2Int(),
				unknownA = reader.readInt(),
				unknownB = reader.readInt(),
				unknownC = reader.readInt(),
				id = reader.readInt(),
				type = reader.readInt(),
				boss = Race.readFrom(reader),
				level = reader.readInt(),
				unknownE = reader.readByte(),
				state = State.readFrom(reader),
				padding = reader.readShort(),
				currentHP = reader.readInt(),
				maxHP = reader.readInt(),
				chunk = reader.readVector2Int()
			)
	}

	enum class State : CwSerializableEnumByte {
		Ready,
		InProgress,
		Finished;

		companion object : CwEnumByteDeserializer<State> {
			override val values = values()
		}
	}
}
