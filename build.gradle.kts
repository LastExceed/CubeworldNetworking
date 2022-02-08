plugins {
	kotlin("jvm") version "1.6.10"
	`maven-publish`
}
group = "com.github.lastexceed"
version = "1.1.0"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
	setOf(
		"io",
		"utils"
	).forEach {
		implementation("io.ktor", "ktor-$it", "2.0.+")
	}
}

publishing {
	publications {
		create<MavenPublication>(project.name) {
			from(components["java"])
		}
	}
}