plugins {
    id("java")
}

group = "io.github.lijinhong11"
version = "1.0-SNAPSHOT"

subprojects {
    apply {
        repositories {
            maven("https://maven.fabricmc.net")
            maven("https://maven.latvian.dev/releases")
            maven("https://jitpack.io")
            maven("https://maven.architectury.dev/")
            mavenCentral()
        }
    }
}

dependencies {
}

tasks.test {
    useJUnitPlatform()
}