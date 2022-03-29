package com.github.lastexceed.cubeworldnetworking.packets

import com.github.lastexceed.cubeworldnetworking.utils.*
import kotlin.math.*

data class Item(
	val typeMajor: Type.Major,
	val typeMinor: Type.Minor,
	val paddingA: Short,
	val randomSeed: Int,
	val recipe: Type.Major,
	val paddingB: Byte,
	val paddingC: Short,
	val rarity: Rarity,
	val material: Material,
	val flags: FlagSet<Flag>,
	val paddingD: Byte,
	val level: Short,
	val paddingE: Short,
	val spirits: Array<Spirit>,
	val spiritCounter: Int
) : SubPacket {
	override suspend fun writeTo(writer: Writer) {
		typeMajor.writeTo(writer)
		writer.writeByte(typeMinor.value)
		writer.writeShort(paddingA)
		writer.writeInt(randomSeed)
		recipe.writeTo(writer)
		writer.writeByte(paddingB)
		writer.writeShort(paddingC)
		rarity.writeTo(writer)
		writer.writeByte(material.serialized)
		writer.writeByte(flags.inner.toByte())
		writer.writeByte(paddingD)
		writer.writeShort(level)
		writer.writeShort(paddingE)
		spirits.forEach { it.writeTo(writer) }
		writer.writeInt(spiritCounter)
	}

	val hp: Float
		get() {
			if (typeMajor.ordinal !in 3..7) return 0f //todo: don't use ordinal here

			val multiplierTypeMajor = if (typeMajor == Type.Major.Chest) 1f else 0.5f

			val hp_mod = (8U * randomSeed.toUInt() % 21U).toInt()
			val multiplierRandom = 2f - (hp_mod / 20f) + when (material) {
				Material.Iron -> 1f
				Material.Linen -> 0.5f
				Material.Cotton -> 0.75f
				else -> 0f
			}

			val multiplierRarity = 2f.pow(rarity.ordinal * 0.25f)

			val lvl = level + spiritCounter * 0.1f
			val multiplierLevel = 2f.pow((1f - Utils.levelScalingFactor(lvl)) * 3f)

			return listOf(
				multiplierLevel,
				multiplierRandom,
				multiplierRarity,
				multiplierTypeMajor
			).fold(5f) { accumulator, multiplier ->
				accumulator * multiplier
			}
		}

	companion object {
		internal suspend fun readFrom(reader: Reader) =
			Item(
				typeMajor = Type.Major.readFrom(reader),
				typeMinor = Type.Minor(reader.readByte()),
				paddingA = reader.readShort(),
				randomSeed = reader.readInt(),
				recipe = Type.Major.readFrom(reader),
				paddingB = reader.readByte(),
				paddingC = reader.readShort(),
				rarity = Rarity.readFrom(reader),
				material = Material.readFrom(reader),
				flags = FlagSet(reader.readByte().toBooleanArray()),
				paddingD = reader.readByte(),
				level = reader.readShort(),
				paddingE = reader.readShort(),
				spirits = Array(32) { Spirit.readFrom(reader) },
				spiritCounter = reader.readInt()
			)
	}

	data class Spirit(
		val position: Vector3<Byte>,
		val material: Material,
		val level: Short,
		val padding: Short
	) : SubPacket {
		override suspend fun writeTo(writer: Writer) {
			writer.writeVector3Byte(position)
			writer.writeByte(material.serialized)
			writer.writeShort(level)
			writer.writeShort(padding)
		}

		companion object {
			internal suspend fun readFrom(reader: Reader) =
				Spirit(
					position = reader.readVector3Byte(),
					material = Material.readFrom(reader),
					level = reader.readShort(),
					padding = reader.readShort()
				)
		}
	}

	object Type {
		enum class Major : CwSerializableEnumByte {
			None,
			Food,
			Formula,
			Weapon,
			Chest,
			Gloves,
			Boots,
			Shoulder,
			Amulet,
			Ring,
			Block,
			Resource,
			Coin,
			PlatinumCoin,
			Leftovers,
			Beak,
			Painting,
			Vase,
			Candle,
			Pet,
			PetFood,
			Quest,
			Unknown,
			Special,
			Lamp,
			ManaCube;

			companion object : CwEnumByteDeserializer<Major> {
				override val values = values()
			}
		}

		@JvmInline
		value class Minor(val value: Byte) {//todo: enum?
		object Food {
			val Cookie = Minor(0)
			val LifePotion = Minor(1)
			val CactusPotion = Minor(2)
			val ManaPotion = Minor(3)
			val GinsengSoup = Minor(4)
			val SnowberryMash = Minor(5)
			val MushroomSpit = Minor(6)
			val Bomb = Minor(7)
			val PineappleSlice = Minor(8)
			val PumpkinMuffin = Minor(9)
		}

			object Weapon {
				val Sword = Minor(0)
				val Axe = Minor(1)
				val Mace = Minor(2)
				val Dagger = Minor(3)
				val Fist = Minor(4)
				val Longsword = Minor(5)
				val Bow = Minor(6)
				val Crossbow = Minor(7)
				val Boomerang = Minor(8)
				val Arrow = Minor(9)
				val Staff = Minor(10)
				val Wand = Minor(11)
				val Bracelet = Minor(12)
				val Shield = Minor(13)
				val Quiver = Minor(14)
				val Greatsword = Minor(15)
				val Greataxe = Minor(16)
				val Greatmace = Minor(17)
				val Pitchfork = Minor(18)
				val Pickaxe = Minor(19)
				val Torch = Minor(20)
			}

			object Resource {
				val Nugget = Minor(0)
				val Log = Minor(1)
				val Feather = Minor(2)
				val Horn = Minor(3)
				val Claw = Minor(4)
				val Fiber = Minor(5)
				val Cobweb = Minor(6)
				val Hair = Minor(7)
				val Crystal = Minor(8)
				val Yarn = Minor(9)
				val Cube = Minor(10)
				val Capsule = Minor(11)
				val Flask = Minor(12)
				val Orb = Minor(13)
				val Spirit = Minor(14)
				val Mushroom = Minor(15)
				val Pumpkin = Minor(16)
				val Pineapple = Minor(17)
				val RadishSlice = Minor(18)
				val ShimmerMushroom = Minor(19)
				val GinsengRoot = Minor(20)
				val OnionSlice = Minor(21)
				val Heartflower = Minor(22)
				val PricklyPear = Minor(23)
				val Iceflower = Minor(24)
				val Soulflower = Minor(25)
				val WaterFlask = Minor(26)
				val Snowberry = Minor(27)
			}

			object Candle {
				val Red = Minor(0)
				val Green = Minor(1)
			}

			object Quest {
				val AmuletYellow = Minor(0)
				val AmuletBlue = Minor(1)
				val Jewelcase = Minor(2)
				val Key = Minor(3)
				val Medicine = Minor(4)
				val Anitvenom = Minor(5)
				val Bandaid = Minor(6)
				val Crutch = Minor(7)
				val Bandage = Minor(8)
				val Salve = Minor(9)
			}

			object Special {
				val HangGlider = Minor(0)
				val Boat = Minor(1)
			}
		}
	}

	enum class Rarity : CwSerializableEnumByte {
		Normal,
		Uncommon,
		Rare,
		Epic,
		Legendary,
		Mythic;

		companion object : CwEnumByteDeserializer<Rarity> {
			override val values = values()
		}
	}

	enum class Material(override val serialized: Byte) : CwSerializableEnumByte {
		None(0),
		Iron(1),
		Wood(2),


		Obsidian(5),
		Unknown(6),
		Bone(7),



		Gold(11),
		Silver(12),
		Emerald(13),
		Sapphire(14),
		Ruby(15),
		Diamond(16),
		Sandstone(17),
		Saurian(18),
		Parrot(19),
		Mammoth(20),
		Plant(21),
		Ice(22),
		Licht(23),
		Glass(24),
		Silk(25),
		Linen(26),
		Cotton(27),

		Fire(-128),
		Unholy(-127),
		IceSpirit(-126),
		Wind(-125);

		companion object : CwEnumByteDeserializer<Material> {
			override val values = values()
		}
	}

	enum class Flag : FlagSetIndex {
		Adapted
	}
}
