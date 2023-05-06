plugins {
    `version-catalog`
    `maven-publish`
    id("dev.bogdanzurac.marp.build.plugins.publishing")
}

catalog {
    versionCatalog {
        from(files("libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])
            groupId = "dev.bogdanzurac.marp.build"
            artifactId = "libs"
            version = "0.0.2"
        }
    }
}