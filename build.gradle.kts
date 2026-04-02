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
            val mavenUser = providers.gradleProperty("mavenUser").orNull
            val mavenPass = providers.gradleProperty("mavenPass").orNull
            if (!mavenUser.isNullOrBlank() && !mavenPass.isNullOrBlank()) {
                credentials {
                    username = mavenUser
                    password = mavenPass
                }
            }
        }
    }
}
