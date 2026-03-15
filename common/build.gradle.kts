plugins {
    id("java")
    id("maven-publish")
}

group = "io.github.lijinhong11.treasury"
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

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "treasury-fabric"
            from(components["java"])
        }
    }

    repositories {
        // add publish repos here
    }
}