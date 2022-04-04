plugins {
	kotlin("jvm") version "1.6.20"
	`maven-publish`
}
group = "com.github.lastexceed"
version = "1.3.42"

repositories {
	mavenCentral()
}

dependencies {
	api("io.ktor", "ktor-network", "2.0.+")
	//technically only need to expose ktor-io, and use ktor-utils internally
	//but consuming this library without ktor-network makes little sense
	//so might as well just expose that since it depends on the other two
}

publishing {
	publications {
		create<MavenPublication>(project.name) {
			from(components["java"])
		}
	}
}