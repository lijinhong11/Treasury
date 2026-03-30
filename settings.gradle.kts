pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases")

        gradlePluginPortal()
    }
}

rootProject.name = "Treasury"

include("api")
include("common")
include("neoforge")
include("fabric")