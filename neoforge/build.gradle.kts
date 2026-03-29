plugins {
    `java-library`
    eclipse
    idea
    id("com.gradleup.shadow") version "9.4.1"
    id("net.neoforged.moddev") version "2.0.141"
}

group = property("maven_group")!!

base {
    archivesName.set("Treasury-NeoForge")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenLocal()
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
    implementation("net.neoforged:neoforge:${property("neo_version")}")

    implementation(project(":api"))
    implementation(project(":common"))

    compileOnly("dev.latvian.mods:kubejs-neoforge:${property("kubejs_neoforge_version")}")
}

tasks.processResources {
    inputs.properties(project.properties)

    filesMatching("META-INF/mods.toml") {
        expand(project.properties)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}