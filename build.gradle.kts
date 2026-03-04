plugins {
    groovy
    `maven-publish`
}

group = "nl.eveoh"
version = "2.1"

repositories {
    maven {
        url = uri("https://maven.eveoh.nl/content/groups/public")
    }
}

dependencies {
    implementation(localGroovy())
    implementation(gradleApi())
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://maven.eveoh.nl/content/repositories/releases")
            if (project.hasProperty("mavenUser")) {
                credentials {
                    username = project.property("mavenUser") as String
                    password = project.property("mavenPass") as String
                }
            }
        }
    }
}
