import java.net.URI
import java.util.*

plugins {
    `kotlin-dsl`
    `maven-publish`
}

dependencies {
    implementation(libs.android.plugin)
    implementation(libs.kotlin.plugin)
}

group = "dev.bogdanzurac.marp.build"
version = "0.0.5"

gradlePlugin {
    plugins {
        register("appPlugin") {
            id = "dev.bogdanzurac.marp.build.plugins.app"
            implementationClass = "dev.bogdanzurac.marp.build.plugins.AppPlugin"
        }
        register("composePlugin") {
            id = "dev.bogdanzurac.marp.build.plugins.compose"
            implementationClass = "dev.bogdanzurac.marp.build.plugins.ComposePlugin"
        }
        register("corePlugin") {
            id = "dev.bogdanzurac.marp.build.plugins.core"
            implementationClass = "dev.bogdanzurac.marp.build.plugins.CorePlugin"
        }
        register("featureDataPlugin") {
            id = "dev.bogdanzurac.marp.build.plugins.feature.data"
            implementationClass = "dev.bogdanzurac.marp.build.plugins.FeatureDataPlugin"
        }
        register("featureDomainPlugin") {
            id = "dev.bogdanzurac.marp.build.plugins.feature.domain"
            implementationClass = "dev.bogdanzurac.marp.build.plugins.FeatureDomainPlugin"
        }
        register("featureUiPlugin") {
            id = "dev.bogdanzurac.marp.build.plugins.feature.ui"
            implementationClass = "dev.bogdanzurac.marp.build.plugins.FeatureUiPlugin"
        }
        register("koinPlugin") {
            id = "dev.bogdanzurac.marp.build.plugins.koin"
            implementationClass = "dev.bogdanzurac.marp.build.plugins.KoinPlugin"
        }
        register("publishingPlugin") {
            id = "dev.bogdanzurac.marp.build.plugins.publishing"
            implementationClass = "dev.bogdanzurac.marp.build.plugins.PublishingPlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            url = URI("https://maven.pkg.github.com/bogdanzurac/marp-android-packages")
            name = "GitHub"
            credentials {
                val properties = Properties()
                properties.load(file("../local.properties").inputStream())
                username = properties["github.username"].toString()
                password = properties["github.password"].toString()
            }
        }
    }
}

tasks.publish {
    dependsOn("check")
}