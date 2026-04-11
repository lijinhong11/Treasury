plugins {
    java
    id("com.gradleup.shadow") version "9.4.1" apply false
    id("fabric-loom") version "1.10-SNAPSHOT" apply false
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