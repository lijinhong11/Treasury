pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.neoforged.net/releases")

        gradlePluginPortal()
    }
}

rootProject.name = "Treasury"

include("api")
include("neoforge")
include("fabric")
include("forge")

include("common")