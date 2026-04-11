plugins {
    java
    idea
    id("com.gradleup.shadow")
    id("fabric-loom")
}

version = project.property("version")!!
group = project.property("maven_group")!!

base {
    archivesName.set("Treasury-Fabric")
}

loom {
    splitEnvironmentSourceSets()

    mods {
        create("fabric") {
            sourceSet(sourceSets["main"])
        }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${project.property("fabric_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_version")}")

    implementation(project(":api"))
    implementation(project(":common"))

    shadow(project(":api"))
    shadow(project(":common", configuration = "namedElements"))
}

tasks.processResources {
    filteringCharset = "UTF-8"

    filesMatching("fabric.mod.json") {
        expand(project.properties)
    }
}

val targetJavaVersion = 17

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"

    options.release.set(targetJavaVersion)
}

tasks.remapJar {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    configurations = listOf(project.configurations.shadow.get())
}

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)

    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}