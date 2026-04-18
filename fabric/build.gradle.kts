import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.io.FileReader
import java.util.Properties

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

sourceSets {
    create("v26_1") {
        resources.srcDirs("src/v26_1/resources")

        compileClasspath += main.get().output
        runtimeClasspath += main.get().output
    }
}

loom {
    splitEnvironmentSourceSets()

    enableTransitiveAccessWideners = false

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

val targetJavaVersion = 17

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"

    options.release.set(targetJavaVersion)
}

tasks.build {
    dependsOn("remapJar", "unmapJar")
}

tasks.remapJar {
    dependsOn("shadowJar")

    archiveBaseName = "Treasury-Fabric-1.20.1~1.21.11"
}

tasks.register<ShadowJar>("unmapJar") {
    dependsOn("processResourcesUnRemap")

    configurations = listOf(project.configurations.shadow.get())

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(layout.buildDirectory.dir("processedResources/unremap"))

    archiveBaseName = "Treasury-Fabric-26.X+"
}

tasks.shadowJar {
    configurations = listOf(project.configurations.shadow.get())

    archiveClassifier = ""
}

tasks.processResources {
    filteringCharset = "UTF-8"

    from(sourceSets.main.get().resources)

    filesMatching("fabric.mod.json") {
        expand(
            project.properties
        )
    }
}

tasks.register<ProcessResources>("processResourcesUnRemap") {
    from("src/v26_1/resources")

    filesMatching("fabric.mod.json") {
        expand(
            project.properties
        )
    }

    into(layout.buildDirectory.dir("processedResources/unremap"))
}

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)

    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}