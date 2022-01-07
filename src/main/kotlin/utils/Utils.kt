package me.lastexceed.cubeworldnetworking.utils

object Utils {
	fun creatureToSoundPosition(creaturePosition: Vector3<Long>): Vector3<Float> {
		return Vector3(
			(creaturePosition.x / 0x10000).toFloat(),
			(creaturePosition.y / 0x10000).toFloat(),
			(creaturePosition.z / 0x10000).toFloat()
		)
	}

	//not sure what this is exactly, but its used for lots of things
	fun levelScalingFactor(level: Float) = 1f / (0.05f * (level - 1f) + 1f)
	fun levelScalingFactor(level: Int) = levelScalingFactor(level.toFloat())

	fun computePower(level: Int) = (101f - 100f * levelScalingFactor(level)).toInt()

	fun computeMaxExperience(level: Int) = (1050f - 1000f * levelScalingFactor(level)).toInt()

	const val SIZE_BLOCK = 0x10000L
	const val SIZE_CHUNK = SIZE_BLOCK * 32L
	const val SIZE_ZONE = SIZE_CHUNK * 8L
	const val SIZE_BIOME = SIZE_ZONE * 64L
	const val SIZE_WORLD = SIZE_BIOME * 1024L
}