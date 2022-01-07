import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.4.31"
	`maven-publish`
}
group = "me.lastexceed"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	setOf(
		"io",
		"utils"
	).forEach {
		implementation("io.ktor", "ktor-$it", "1.5.2")
	}
}

tasks.withType<KotlinCompile>() {
	kotlinOptions {
		jvmTarget = "13"
		freeCompilerArgs = listOf(
			"-XXLanguage:+InlineClasses",
			"-Xopt-in=kotlin.ExperimentalUnsignedTypes"
		)
	}
}

publishing {
	publications {
		create<MavenPublication>(project.name) {
			from(components["java"])
		}
	}
}