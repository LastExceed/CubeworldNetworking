package com.github.lastexceed.cubeworldnetworking.utils

import com.github.lastexceed.cubeworldnetworking.packets.*

object Utils {
	fun creatureToSoundPosition(creaturePosition: Vector3<Long>) =
		Vector3(
			(creaturePosition.x / 0x10000).toFloat(),
			(creaturePosition.y / 0x10000).toFloat(),
			(creaturePosition.z / 0x10000).toFloat()
		)

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

fun WorldUpdate.Companion.from(subPacket: WorldUpdate.SubPacket) {
	WorldUpdate(
		worldEdits = listOfNotNull(subPacket as? WorldEdit),
		hits = listOfNotNull(subPacket as? Hit),
		particles = listOfNotNull(subPacket as? Particle),
		soundEffects = listOfNotNull(subPacket as? SoundEffect),
		projectiles = listOfNotNull(subPacket as? Projectile),
		worldObjects = listOfNotNull(subPacket as? WorldObject),
		chunkLoots = listOfNotNull(subPacket as? ChunkLoot),
		p48s = listOfNotNull(subPacket as? P48),
		pickups = listOfNotNull(subPacket as? Pickup),
		kills = listOfNotNull(subPacket as? Kill),
		attacks = listOfNotNull(subPacket as? Attack),
		statusEffects = listOfNotNull(subPacket as? StatusEffect),
		missions = listOfNotNull(subPacket as? Mission),
	)
}