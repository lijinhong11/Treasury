import com.vanniktech.maven.publish.DeploymentValidation
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SourcesJar

plugins {
    java
    `maven-publish`
    signing
    id("com.vanniktech.maven.publish") version "0.36.0"
}

group = "io.github.lijinhong11"
version = project.properties["version"] as String

repositories {
    mavenCentral()
}

dependencies {
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = true, validateDeployment = DeploymentValidation.PUBLISHED)

    signAllPublications()

    coordinates("io.github.lijinhong11", "Treasury", project.version.toString())

    pom {
        name.set(project.name)
        description.set("Treasury API")
        url.set("https://github.com/lijinhong11/Treasury")

        licenses {
            license {
                name.set("GPL 3.0 License")
                url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
            }
        }

        developers {
            developer {
                id.set("lijinhong11")
                name.set("Jinhong Li")
                email.set("tygfhk@outlook.com")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/lijinhong11/Treasury.git")
            developerConnection.set("scm:git:ssh://github.com:lijinhong11/Treasury.git")
            url.set("https://github.com/lijinhong11/Treasury")
        }
    }

    configureBasedOnAppliedPlugins(
        // configures the -javadoc artifact, possible values:
        // - `JavadocJar.None()` don't publish this artifact
        // - `JavadocJar.Empty()` publish an empty jar
        // - `JavadocJar.Javadoc()` to publish standard javadocs
        // - `JavadocJar.Dokka("dokkaHtml")` when using Kotlin with Dokka, where `dokkaHtml` is the name of the Dokka task that should be used as input
        javadocJar = JavadocJar.Javadoc(),
        // configures the -sources artifact, possible values:
        // - `SourcesJar.None()` don't publish this artifact
        // - `SourcesJar.Empty()` publish an empty jar
        // - `SourcesJar.Sources()` publish the sources
        sourcesJar = SourcesJar.Sources()
    )
}