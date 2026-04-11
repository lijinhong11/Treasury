plugins {
    java
    idea
    id("com.gradleup.shadow")
    id("net.neoforged.moddev") version "2.0.141"
}

group = property("maven_group")!!

base {
    archivesName.set("Treasury-NeoForge")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

neoForge {
    version = property("neo_version") as String

    runs {
        create("client") {
            type = "client"

            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
            systemProperty("forge.enabledGameTestNamespaces", "treasury")
        }

        create("server") {
            type = "server"

            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
            systemProperty("forge.enabledGameTestNamespaces", "treasury")
            programArgument("--nogui")
        }

        create("gameTestServer") {
            type = "gameTestServer"

            systemProperty("forge.enabledGameTestNamespaces", "treasury")
        }

        create("data") {
            type = "data"

            programArguments.addAll(
                    "--mod", "treasury",
                    "--all",
                    "--output", file("src/generated/resources").absolutePath,
                    "--existing", file("src/main/resources").absolutePath
            )
        }
    }
}

sourceSets {
    main {
        resources.srcDir("src/generated/resources")
    }
}

dependencies {
    implementation("net.neoforged:neoforge:${project.property("neo_version")}")

    implementation(project(":api"))
    implementation(project(":common"))

    shadow(project(":api"))
    shadow(project(":common", configuration = "namedElements"))
}

tasks.processResources {
    val prop = mapOf(
        "version" to version,
        "neoforge_version_range" to "[20,)",
        "description" to description
    )

    inputs.properties(prop)

    filesMatching("META-INF/mods.toml") {
        expand(prop)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.shadowJar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    configurations = listOf(project.configurations.shadow.get())

    archiveClassifier.set("")
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}