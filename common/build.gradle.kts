plugins {
    id("java")
    id("fabric-loom") version "1.10-SNAPSHOT"
}

group = "io.github.lijinhong11"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings(loom.officialMojangMappings())

    implementation(project(":api"))

    compileOnly("net.luckperms:api:5.4")
}

tasks.test {
    useJUnitPlatform()
}