package com.github.lastexceed.cubeworldnetworking.packets

import io.ktor.util.toByteArray
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.close
import com.github.lastexceed.cubeworldnetworking.utils.*

data class CreatureUpdate(
	val id: CreatureID,
	val position: Vector3<Long>? = null,
	val rotation: Vector3<Float>? = null,
	val velocity: Vector3<Float>? = null,
	val acceleration: Vector3<Float>? = null,
	/**used by the 'retreat' ability*/
	val velocityExtra: Vector3<Float>? = null,
	val climbAnimationState: Float? = null,
	val flagsPhysics: FlagSet<PhysicsFlag>? = null,
	val affiliation: Affiliation? = null,
	val race: Race? = null,
	val animation: Animation? = null,
	val animationTime: Int? = null,
	val combo: Int? = null,
	val hitTimeOut: Int? = null,
	val appearance: Appearance? = null,
	val flags: FlagSet<CreatureFlag>? = null,
	val effectTimeDodge: Int? = null,
	val effectTimeStun: Int? = null,
	val effectTimeFear: Int? = null,
	val effectTimeIce: Int? = null,
	val effectTimeWind: Int? = null,
	/**unknown purpose, name adopted from cuwo*/
	val showPatchTime: Int? = null,
	val combatClassMajor: CombatClassMajor? = null,
	val combatClassMinor: CombatClassMinor? = null,
	val manaCharge: Float? = null,
	val unknown24: Vector3<Float>? = null,
	val unknown25: Vector3<Float>? = null,
	/**coordinates of the location this creature is aiming at, relative to its own position*/
	val aimDisplacement: Vector3<Float>? = null,
	val health: Float? = null,
	val mana: Float? = null,
	val blockingGauge: Float? = null,
	val multipliers: Multipliers? = null,
	val unknown31: Byte? = null,
	val unknown32: Byte? = null,
	val level: Int? = null,
	val experience: Int? = null,
	/**for pets this is the [CreatureID] of their owner*/
	val master: CreatureID? = null,
	val unknown36: Long? = null,
	/**this is the '+#' that monsters in some dungeons have next to their [race]*/
	val powerBase: Byte? = null,
	val unknown38: Int? = null,
	val unknown39: Vector3<Int>? = null,
	val home: Vector3<Long>? = null,
	/**players within ±2 [level] of the dungeon at these coordinates see a green speech bubble above this creature's head and can get that chunk revealed on the map by talking to this creature*/
	val chunkToReveal: Vector3<Int>? = null,
	val unknown42: Byte? = null,//0 3 4 for villages - 3 = dialog about pet food
	val consumable: Item? = null,
	val equipment: Equipment? = null,
	val name: String? = null,
	val skillPointDistribution: SkillDistribution? = null,
	val manaCubes: Int? = null
) : Packet(PacketId.CreatureUpdate) {
	override suspend fun writeTo(writer: Writer) {
		val optionalChannel = ByteChannel(true)
		val optionalDataWriter = Writer(optionalChannel)
		val mask = BooleanArray(Long.SIZE_BITS)

		position?.let {
			optionalDataWriter.writeVector3Long(it)
			mask[0] = true
		}
		rotation?.let {
			optionalDataWriter.writeVector3Float(it)
			mask[1] = true
		}
		velocity?.let {
			optionalDataWriter.writeVector3Float(it)
			mask[2] = true
		}
		acceleration?.let {
			optionalDataWriter.writeVector3Float(it)
			mask[3] = true
		}
		velocityExtra?.let {
			optionalDataWriter.writeVector3Float(it)
			mask[4] = true
		}
		climbAnimationState?.let {
			optionalDataWriter.writeFloat(it)
			mask[5] = true
		}
		flagsPhysics?.let {
			optionalDataWriter.writeInt(it.inner.toInt())
			mask[6] = true
		}
		affiliation?.run {
			writeTo(optionalDataWriter)
			mask[7] = true
		}
		race?.run {
			writeTo(optionalDataWriter)
			mask[8] = true
		}
		animation?.run {
			writeTo(optionalDataWriter)
			mask[9] = true
		}
		animationTime?.let {
			optionalDataWriter.writeInt(it)
			mask[10] = true
		}
		combo?.let {
			optionalDataWriter.writeInt(it)
			mask[11] = true
		}
		hitTimeOut?.let {
			optionalDataWriter.writeInt(it)
			mask[12] = true
		}
		appearance?.let {
			it.writeTo(optionalDataWriter)
			mask[13] = true
		}
		flags?.let {
			optionalDataWriter.writeShort(it.inner.toShort())
			mask[14] = true
		}
		effectTimeDodge?.let {
			optionalDataWriter.writeInt(it)
			mask[15] = true
		}
		effectTimeStun?.let {
			optionalDataWriter.writeInt(it)
			mask[16] = true
		}
		effectTimeFear?.let {
			optionalDataWriter.writeInt(it)
			mask[17] = true
		}
		effectTimeIce?.let {
			optionalDataWriter.writeInt(it)
			mask[18] = true
		}
		effectTimeWind?.let {
			optionalDataWriter.writeInt(it)
			mask[19] = true
		}
		showPatchTime?.let {
			optionalDataWriter.writeInt(it)
			mask[20] = true
		}
		combatClassMajor?.run {
			writeTo(optionalDataWriter)
			mask[21] = true
		}
		combatClassMinor?.run {
			writeTo(optionalDataWriter)
			mask[22] = true
		}
		manaCharge?.let {
			optionalDataWriter.writeFloat(it)
			mask[23] = true
		}
		unknown24?.let {
			optionalDataWriter.writeVector3Float(it)
			mask[24] = true
		}
		unknown25?.let {
			optionalDataWriter.writeVector3Float(it)
			mask[25] = true
		}
		aimDisplacement?.let {
			optionalDataWriter.writeVector3Float(it)
			mask[26] = true
		}
		health?.let {
			optionalDataWriter.writeFloat(it)
			mask[27] = true
		}
		mana?.let {
			optionalDataWriter.writeFloat(it)
			mask[28] = true
		}
		blockingGauge?.let {
			optionalDataWriter.writeFloat(it)
			mask[29] = true
		}
		multipliers?.let {
			it.writeTo(optionalDataWriter)
			mask[30] = true
		}
		unknown31?.let {
			optionalDataWriter.writeByte(it)
			mask[31] = true
		}
		unknown32?.let {
			optionalDataWriter.writeByte(it)
			mask[32] = true
		}
		level?.let {
			optionalDataWriter.writeInt(it)
			mask[33] = true
		}
		experience?.let {
			optionalDataWriter.writeInt(it)
			mask[34] = true
		}
		master?.let {
			optionalDataWriter.writeLong(it.value)
			mask[35] = true
		}
		unknown36?.let {
			optionalDataWriter.writeLong(it)
			mask[36] = true
		}
		powerBase?.let {
			optionalDataWriter.writeByte(it)
			mask[37] = true
		}
		unknown38?.let {
			optionalDataWriter.writeInt(it)
			mask[38] = true
		}
		unknown39?.let {
			optionalDataWriter.writeVector3Int(it)
			mask[39] = true
		}
		home?.let {
			optionalDataWriter.writeVector3Long(it)
			mask[40] = true
		}
		chunkToReveal?.let {
			optionalDataWriter.writeVector3Int(it)
			mask[41] = true
		}
		unknown42?.let {
			optionalDataWriter.writeByte(it)
			mask[42] = true
		}
		consumable?.let {
			it.writeTo(optionalDataWriter)
			mask[43] = true
		}
		equipment?.let {
			it.writeTo(optionalDataWriter)
			mask[44] = true
		}
		name?.let {
			val nameBytes = ByteArray(16)
			it.toByteArray(Charsets.UTF_8).copyInto(nameBytes)
			optionalDataWriter.writeByteArray(nameBytes)
			mask[45] = true
		}
		skillPointDistribution?.let {
			it.writeTo(optionalDataWriter)
			mask[46] = true
		}
		manaCubes?.let {
			optionalDataWriter.writeInt(it)
			mask[47] = true
		}

		optionalChannel.close()
		val optionalData = optionalChannel.toByteArray()

		val inflatedChannel = ByteChannel(true)
		val inflatedWriter = Writer(inflatedChannel)

		inflatedWriter.writeLong(id.value)
		inflatedWriter.writeLong(mask.toLong())
		inflatedWriter.writeByteArray(optionalData)

		inflatedChannel.close()
		val deflated = Zlib.deflate(inflatedChannel.toByteArray())

		writer.writeInt(deflated.size)
		writer.writeByteArray(deflated)
	}

	companion object : CwDeserializer<CreatureUpdate> {
		override suspend fun readFrom(reader: Reader): CreatureUpdate {
			val length = reader.readInt()
			val deflated = reader.readByteArray(length)
			val inflated = Zlib.inflate(deflated)
			val inflatedReader = Reader(inflated)

			val id = CreatureID(inflatedReader.readLong())
			val mask = inflatedReader.readLong().toBooleanArray()

			return CreatureUpdate(
				id = id,
				position = if (mask[0]) inflatedReader.readVector3Long() else null,
				rotation = if (mask[1]) inflatedReader.readVector3Float() else null,
				velocity = if (mask[2]) inflatedReader.readVector3Float() else null,
				acceleration = if (mask[3]) inflatedReader.readVector3Float() else null,
				velocityExtra = if (mask[4]) inflatedReader.readVector3Float() else null,
				climbAnimationState = if (mask[5]) inflatedReader.readFloat() else null,
				flagsPhysics = if (mask[6]) FlagSet(inflatedReader.readInt().toBooleanArray()) else null,
				affiliation = if (mask[7]) Affiliation.readFrom(inflatedReader) else null,
				race = if (mask[8]) Race.readFrom(inflatedReader) else null,
				animation = if (mask[9]) Animation.readFrom(inflatedReader) else null,
				animationTime = if (mask[10]) inflatedReader.readInt() else null,
				combo = if (mask[11]) inflatedReader.readInt() else null,
				hitTimeOut = if (mask[12]) inflatedReader.readInt() else null,
				appearance = if (mask[13]) Appearance.readFrom(inflatedReader) else null,
				flags = if (mask[14]) FlagSet(inflatedReader.readShort().toBooleanArray()) else null,
				effectTimeDodge = if (mask[15]) inflatedReader.readInt() else null,
				effectTimeStun = if (mask[16]) inflatedReader.readInt() else null,
				effectTimeFear = if (mask[17]) inflatedReader.readInt() else null,
				effectTimeIce = if (mask[18]) inflatedReader.readInt() else null,
				effectTimeWind = if (mask[19]) inflatedReader.readInt() else null,
				showPatchTime = if (mask[20]) inflatedReader.readInt() else null,
				combatClassMajor = if (mask[21]) CombatClassMajor.readFrom(inflatedReader) else null,
				combatClassMinor = if (mask[22]) CombatClassMinor.readFrom(inflatedReader)else null,
				manaCharge = if (mask[23]) inflatedReader.readFloat() else null,
				unknown24 = if (mask[24]) inflatedReader.readVector3Float() else null,
				unknown25 = if (mask[25]) inflatedReader.readVector3Float() else null,
				aimDisplacement = if (mask[26]) inflatedReader.readVector3Float() else null,
				health = if (mask[27]) inflatedReader.readFloat() else null,
				mana = if (mask[28]) inflatedReader.readFloat() else null,
				blockingGauge = if (mask[29]) inflatedReader.readFloat() else null,
				multipliers = if (mask[30]) Multipliers.readFrom(inflatedReader) else null,
				unknown31 = if (mask[31]) inflatedReader.readByte() else null,
				unknown32 = if (mask[32]) inflatedReader.readByte() else null,
				level = if (mask[33]) inflatedReader.readInt() else null,
				experience = if (mask[34]) inflatedReader.readInt() else null,
				master = if (mask[35]) CreatureID(inflatedReader.readLong()) else null,
				unknown36 = if (mask[36]) inflatedReader.readLong() else null,
				powerBase = if (mask[37]) inflatedReader.readByte() else null,
				unknown38 = if (mask[38]) inflatedReader.readInt() else null,
				unknown39 = if (mask[39]) inflatedReader.readVector3Int() else null,
				home = if (mask[40]) inflatedReader.readVector3Long() else null,
				chunkToReveal = if (mask[41]) inflatedReader.readVector3Int() else null,
				unknown42 = if (mask[42]) inflatedReader.readByte() else null,
				consumable = if (mask[43]) Item.readFrom(inflatedReader) else null,
				equipment = if (mask[44]) Equipment.readFrom(inflatedReader) else null,
				name = if (mask[45]) inflatedReader.readByteArray(16).toString(Charsets.UTF_8).trimEnd(Char.MIN_VALUE) else null,
				skillPointDistribution = if (mask[46]) SkillDistribution.readFrom(inflatedReader) else null,
				manaCubes = if (mask[47]) inflatedReader.readInt() else null
			)
		}
	}
}

@JvmInline
value class CreatureID(val value: Long)

data class Appearance(
	var unknownA: Byte,
	var unknownB: Byte,
	var hairColor: Vector3<Byte>,
	var unknownC: Byte,
	var flags: FlagSet<AppearanceFlag>,
	var creatureSize: Vector3<Float>,
	var headModel: Short,
	var hairModel: Short,
	var handModel: Short,
	var footModel: Short,
	var bodyModel: Short,
	var tailModel: Short,
	var shoulder2Model: Short,
	var wingModel: Short,
	var headSize: Float,
	var bodySize: Float,
	var handSize: Float,
	var footSize: Float,
	var shoulder2Size: Float,
	var weaponSize: Float,
	var tailSize: Float,
	var shoulder1Size: Float,
	var wingSize: Float,
	var bodyRotation: Float,
	var handRotation: Vector3<Float>,
	var feetRotation: Float,
	var wingRotation: Float,
	var tailRotation: Float,
	var bodyOffset: Vector3<Float>,
	var headOffset: Vector3<Float>,
	var handOffset: Vector3<Float>,
	var footOffset: Vector3<Float>,
	var tailOffset: Vector3<Float>,
	var wingOffset: Vector3<Float>
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeByte(unknownA)
		writer.writeByte(unknownB)
		writer.writeVector3Byte(hairColor)
		writer.writeByte(unknownC)
		writer.writeShort(flags.inner.toShort())
		writer.writeVector3Float(creatureSize)
		writer.writeShort(headModel)
		writer.writeShort(hairModel)
		writer.writeShort(handModel)
		writer.writeShort(footModel)
		writer.writeShort(bodyModel)
		writer.writeShort(tailModel)
		writer.writeShort(shoulder2Model)
		writer.writeShort(wingModel)
		writer.writeFloat(headSize)
		writer.writeFloat(bodySize)
		writer.writeFloat(handSize)
		writer.writeFloat(footSize)
		writer.writeFloat(shoulder2Size)
		writer.writeFloat(weaponSize)
		writer.writeFloat(tailSize)
		writer.writeFloat(shoulder1Size)
		writer.writeFloat(wingSize)
		writer.writeFloat(bodyRotation)
		writer.writeVector3Float(handRotation)
		writer.writeFloat(feetRotation)
		writer.writeFloat(wingRotation)
		writer.writeFloat(tailRotation)
		writer.writeVector3Float(bodyOffset)
		writer.writeVector3Float(headOffset)
		writer.writeVector3Float(handOffset)
		writer.writeVector3Float(footOffset)
		writer.writeVector3Float(tailOffset)
		writer.writeVector3Float(wingOffset)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Appearance(
				unknownA = reader.readByte(),
				unknownB = reader.readByte(),
				hairColor = reader.readVector3Byte(),
				unknownC = reader.readByte(),
				flags = FlagSet(reader.readShort().toBooleanArray()),
				creatureSize = reader.readVector3Float(),
				headModel = reader.readShort(),
				hairModel = reader.readShort(),
				handModel = reader.readShort(),
				footModel = reader.readShort(),
				bodyModel = reader.readShort(),
				tailModel = reader.readShort(),
				shoulder2Model = reader.readShort(),
				wingModel = reader.readShort(),
				headSize = reader.readFloat(),
				bodySize = reader.readFloat(),
				handSize = reader.readFloat(),
				footSize = reader.readFloat(),
				shoulder2Size = reader.readFloat(),
				weaponSize = reader.readFloat(),
				tailSize = reader.readFloat(),
				shoulder1Size = reader.readFloat(),
				wingSize = reader.readFloat(),
				bodyRotation = reader.readFloat(),
				handRotation = reader.readVector3Float(),
				feetRotation = reader.readFloat(),
				wingRotation = reader.readFloat(),
				tailRotation = reader.readFloat(),
				bodyOffset = reader.readVector3Float(),
				headOffset = reader.readVector3Float(),
				handOffset = reader.readVector3Float(),
				footOffset = reader.readVector3Float(),
				tailOffset = reader.readVector3Float(),
				wingOffset = reader.readVector3Float()
			)
	}
}

data class Multipliers(
	var health: Float,
	var attackSpeed: Float,
	var damage: Float,
	var armor: Float,
	var resi: Float
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeFloat(health)
		writer.writeFloat(attackSpeed)
		writer.writeFloat(damage)
		writer.writeFloat(armor)
		writer.writeFloat(resi)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Multipliers(
				health = reader.readFloat(),
				attackSpeed = reader.readFloat(),
				damage = reader.readFloat(),
				armor = reader.readFloat(),
				resi = reader.readFloat()
			)
	}
}

data class Equipment(
	var unknown: Item,
	var neck: Item,
	var chest: Item,
	var feet: Item,
	var hands: Item,
	var shoulder: Item,
	var leftWeapon: Item,
	var rightWeapon: Item,
	var leftRing: Item,
	var rightRing: Item,
	var lamp: Item,
	var special: Item,
	var pet: Item
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		unknown.writeTo(writer)
		neck.writeTo(writer)
		chest.writeTo(writer)
		feet.writeTo(writer)
		hands.writeTo(writer)
		shoulder.writeTo(writer)
		leftWeapon.writeTo(writer)
		rightWeapon.writeTo(writer)
		leftRing.writeTo(writer)
		rightRing.writeTo(writer)
		lamp.writeTo(writer)
		special.writeTo(writer)
		pet.writeTo(writer)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Equipment(
				unknown = Item.readFrom(reader),
				neck = Item.readFrom(reader),
				chest = Item.readFrom(reader),
				feet = Item.readFrom(reader),
				hands = Item.readFrom(reader),
				shoulder = Item.readFrom(reader),
				leftWeapon = Item.readFrom(reader),
				rightWeapon = Item.readFrom(reader),
				leftRing = Item.readFrom(reader),
				rightRing = Item.readFrom(reader),
				lamp = Item.readFrom(reader),
				special = Item.readFrom(reader),
				pet = Item.readFrom(reader)
			)
	}
}

data class SkillDistribution(
	var petMaster: Int,
	var petRiding: Int,
	var sailing: Int,
	var climbing: Int,
	var hangGliding: Int,
	var swimming: Int,
	var ability1: Int,
	var ability2: Int,
	var ability3: Int,
	var ability4: Int,
	var ability5: Int
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		writer.writeInt(petMaster)
		writer.writeInt(petRiding)
		writer.writeInt(sailing)
		writer.writeInt(climbing)
		writer.writeInt(hangGliding)
		writer.writeInt(swimming)
		writer.writeInt(ability1)
		writer.writeInt(ability2)
		writer.writeInt(ability3)
		writer.writeInt(ability4)
		writer.writeInt(ability5)
	}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			SkillDistribution(
				petMaster = reader.readInt(),
				petRiding = reader.readInt(),
				sailing = reader.readInt(),
				climbing = reader.readInt(),
				hangGliding = reader.readInt(),
				swimming = reader.readInt(),
				ability1 = reader.readInt(),
				ability2 = reader.readInt(),
				ability3 = reader.readInt(),
				ability4 = reader.readInt(),
				ability5 = reader.readInt()
			)
	}
}

enum class PhysicsFlag : FlagSetIndex {
	OnGround,
	Swimming,
	TouchingWall,
	Unknown3,
	Unknown4, //always true
	PushingWall,
	PushingObject,
	Unknown7
}

enum class CreatureFlag : FlagSetIndex {
	Climbing,
	Unknown1,
	Aiming,
	Unknown3,
	Gliding,
	FriendlyFire,
	Sprinting,
	Unknown7,
	Unknown8,
	Lamp,
	Sniping,
	Unknown11,
	Unknown12,
	Unknown13,
	Unknown14,
	Unknown15,
}

enum class AppearanceFlag : FlagSetIndex {
	FourLegged,
	CanFly,
	Unknown2,
	Unknown3,
	Unknown4,
	Unknown5,
	Unknown6,
	Unknown7,
	LockedInPlace, //used by dummies
	BossGlow,
	Unknown10,
	Unknown11,
	Unknown12, //used by bosses
	Invincible, //used by dummies
	Unknown14,
	Unknown15,
}

enum class Affiliation : CwSerializableEnumByte {
	Player,
	Enemy,
	Unknown2,
	NPC,
	Unknown4,
	Pet,
	Neutral;

	companion object : CwEnumByteDeserializer<Affiliation> {
		override val values = values()
	}
}

enum class Race : CwSerializableEnumInt {
	ElfMale,
	ElfFemale,
	HumanMale,
	HumanFemale,
	GoblinMale,
	GoblinFemale,
	TerrierBull,
	LizardmanMale,
	LizardmanFemale,
	DwarfMale,
	DwarfFemale,
	OrcMale,
	OrcFemale,
	FrogmanMale,
	FrogmanFemale,
	UndeadMale,
	UndeadFemale,
	Skeleton,
	OldMan,
	Collie,
	ShepherdDog,
	SkullBull,
	Alpaca,
	AlpacaBrown,
	Egg,
	Turtle,
	Terrier,
	TerrierScottish,
	Wolf,
	Panther,
	Cat,
	CatBrown,
	CatWhite,
	Pig,
	Sheep,
	Bunny,
	Porcupine,
	SlimeGreen,
	SlimePink,
	SlimeYellow,
	SlimeBlue,
	Frightener,
	Sandhorror,
	Wizard,
	Bandit,
	Witch,
	Ogre,
	Rockling,
	Gnoll,
	GnollPolar,
	Monkey,
	Gnobold,
	Insectoid,
	Hornet,
	InsectGuard,
	Crow,
	Chicken,
	Seagull,
	Parrot,
	Bat,
	Fly,
	Midge,
	Mosquito,
	RunnerPlain,
	RunnerLeaf,
	RunnerSnow,
	RunnerDesert,
	Peacock,
	Frog,
	CreaturePlant,
	CreatureRadish,
	Onionling,
	OnionlingDesert,
	Devourer,
	Duckbill,
	Crocodile,
	CreatureSpike,
	Anubis,
	Horus,
	Jester,
	Spectrino,
	Djinn,
	Minotaur,
	NomadMale,
	NomadFemale,
	Imp,
	Spitter,
	Mole,
	Biter,
	Koala,
	Squirrel,
	Raccoon,
	Owl,
	Penguin,
	Werewolf,
	Santa,
	Zombie,
	Vampire,
	Horse,
	Camel,
	Cow,
	Dragon,
	BeetleDark,
	BeetleFire,
	BeetleSnout,
	BeetleLemon,
	Crab,
	CrabSea,
	Troll,
	TrollDark,
	Helldemon,
	Golem,
	GolemEmber,
	GolemSnow,
	Yeti,
	Cyclops,
	Mammoth,
	Lich,
	Runegiant,
	Saurian,
	Bush,
	BushSnow,
	BushSnowberry,
	PlantCotton,
	Scrub,
	ScrubCobweg,
	ScrubFire,
	Ginseng,
	Cactus,
	ChristmasTree,
	Thorntree,
	DepositGold,
	DepositIron,
	DepositSilver,
	DepositSandstone,
	DepositEmerald,
	DepositSapphire,
	DepositRuby,
	DepositDiamond,
	DepositIcecrystal,
	Scarecrow,
	Aim,
	Dummy,
	Vase,
	Bomb,
	FishSapphire,
	FishLemon,
	Seahorse,
	Mermaid,
	Merman,
	Shark,
	Bumblebee,
	Lanternfish,
	Mawfish,
	Piranha,
	Blowfish;

	companion object : CwEnumIntDeserializer<Race> {
		override val values = values()
	}
}

enum class Animation : CwSerializableEnumByte {
	Idle,
	DualWieldM1a,
	DualWieldM1b,
	Unknown003, //like daggers
	Unknown004,
	LongswordM2,
	UnarmedM1a, //fists use these
	UnarmedM1b,
	ShieldM2Charging,
	ShieldM1a,
	ShieldM1b,
	UnarmedM2,
	Unknown012, //swords rip apart
	LongswordM1a,
	LongswordM1b,
	Unknown015, //probably for greatweapon A1
	Unknown016, //same as 17
	DaggersM2,
	DaggersM1a,
	DaggersM1b,
	FistsM2,
	Kick, //same as 20
	ShootArrow,
	CrossbowM2,
	CrossbowM2Charging,
	BowM2Charging,
	BoomerangM1,
	BoomerangM2Charging,
	BeamDraining,
	Unknown029, //nothing
	StaffFireM1,
	StaffFireM2,
	StaffWaterM1,
	StaffWaterM2,
	HealingStream,
	Unknown035, //summon animation
	Unknown036, //wand charging?
	BraceletFireM2,
	WandFireM1,
	BraceletsFireM1a,
	BraceletsFireM1b,
	BraceletsWaterM1a,
	BraceletsWaterM1b,
	BraceletWaterM2,
	WandWaterM1,
	WandWaterM2,
	WandFireM2,
	Unknown047, //same as 54
	Intercept,
	Teleport,
	Unknown050,
	Unknown051, //mob attack?
	Unknown052, //nothing, immediately switches to 0
	Unknown053, //nothing
	Smash,
	BowM2,
	Unknown056, //nothing, causes rotation lock
	GreatweaponM1a,
	GreatweaponM1c,
	GreatweaponM2Charging,
	GreatweaponM2Berserker,
	GreatweaponM2Guardian,
	Unknown062, //probably for greatweapon A2
	UnarmedM2Charging,
	DualWieldM2Charging,
	Unknown065, //probably for greatweapon B1
	Unknown066, //probably for greatweapon B2
	GreatweaponM1b,
	BossCharge1,
	BossCharge2,
	BossSpinkick,
	BossBlock,
	BossSpin,
	BossCry,
	BossStomp,
	BossKick,
	BossKnockdownForward,
	BossKnockdownLeft,
	BossKnockdownRight,
	Stealth,
	Drinking,
	Eating,
	PetFoodPresent,
	Sitting,
	Sleeping,
	Unknown085, //nothing
	Cyclone,
	FireExplosionLong,
	FireExplosionShort,
	Lava,
	Splash,
	EarthQuake,
	Clone,
	Unknown093, //same as 48
	FireBeam,
	FireRay,
	Shuriken,
	Unknown097, //nothing, rotation lock
	Unknown098, //parry? causes blocking
	Unknown099, //nothing, rotation lock
	Unknown100, //nothing
	SuperBulwalk, //casues bulwalk
	Unknown102, //nothing
	SuperManaShield, //causes manashield
	ShieldM2,
	TeleportToCity,
	Riding,
	Boat,
	Boulder,
	ManaCubePickup,
	Unknown110; //mob attack?

	companion object : CwEnumByteDeserializer<Animation> {
		override val values = values()
	}
}

enum class CombatClassMajor(override val serialized: Byte) : CwSerializableEnumByte {
	None(0),
	Warrior(1),
	Ranger(2),
	Mage(3),
	Rogue(4),

	GeneralShopkeep(-128),
	WeaponShopkeep(-127),
	ArmorShopkeep(-126),
	Identifier(-125),
	Innkeep(-124),
	Blacksmith(-123),//no function
	Woodworker(-122),//no function
	Weaver(-121),//no function
	Villager(-120),
	Adapter(-119);

	companion object : CwEnumByteDeserializer<CombatClassMajor> {
		override val values = values()
	}
}

enum class CombatClassMinor : CwSerializableEnumByte {
	Default,
	Alternative,
	Witch; //witch NPCs on vanilla server use this

	companion object : CwEnumByteDeserializer<CombatClassMinor> {
		override val values = values()
	}

	object Warrior {
		val Berserker = Default
		val Guardian = Alternative
	}

	object Ranger {
		val Sniper = Default
		val Scout = Alternative
	}

	object Mage {
		val Fire = Default
		val Water = Alternative
	}

	object Rogue {
		val Assassin = Default
		val Ninja = Alternative
	}
}