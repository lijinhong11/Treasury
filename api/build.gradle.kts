plugins {
    id("java")
    id("io.johnsonlee.sonatype-publish-plugin") version "1.6.1"
}

group = "io.github.lijinhong11"
version = project.properties["version"] as String

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}