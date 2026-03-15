pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.neoforged.net/releases")

        gradlePluginPortal()
    }
}

rootProject.name = "Treasury"

include("neoforge")
include("common")
include("fabric")
include("forge")
