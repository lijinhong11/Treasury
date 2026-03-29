plugins {
    id("java")
    id("fabric-loom") version "1.10-SNAPSHOT"
}

group = "io.github.lijinhong11"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies {
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings(loom.officialMojangMappings())

    implementation(project(":api"))

    api("dev.latvian.mods:kubejs-fabric:${project.property("kubejs_version")}")
}