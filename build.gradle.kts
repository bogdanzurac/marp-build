import java.net.URI
import java.util.*

plugins {
    `version-catalog`
    `maven-publish`
}

catalog {
    versionCatalog {
        from(files("libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("publishLibs") {
            from(components["versionCatalog"])
            groupId = "dev.bogdanzurac.marp.build"
            artifactId = "libs"
            version = "0.0.13"
        }
    }

    repositories {
        maven {
            url = URI("https://maven.pkg.github.com/bogdanzurac/marp-android-packages")
            name = "GitHub"
            credentials {
                val properties = Properties()
                properties.load(project.rootProject.file("local.properties").inputStream())
                username = properties["github.username"].toString()
                password = properties["github.password"].toString()
            }
        }
    }
}