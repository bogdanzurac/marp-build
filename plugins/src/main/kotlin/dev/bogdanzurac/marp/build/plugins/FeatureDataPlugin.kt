package dev.bogdanzurac.marp.build.plugins

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureDataPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {

        with(pluginManager) {
            apply("com.android.library")
            apply("io.realm.kotlin")
            apply("org.jetbrains.kotlin.android")
            apply("org.jetbrains.kotlin.plugin.serialization")
            apply("dev.bogdanzurac.marp.build.plugins.koin")
        }

        configureKotlin(JavaVersion.VERSION_11)

        android {
            compileSdk = versionCatalog.findVersion("androidCompileSdk").intValue
            buildToolsVersion = versionCatalog.findVersion("androidBuildTools").value

            defaultConfig {
                minSdk = versionCatalog.findVersion("androidMinSdk").intValue
            }

            buildFeatures {
                buildConfig = false
                compose = false
                aidl = false
                renderScript = false
                shaders = false
            }

            buildTypes {
                release {
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }

        dependencies {
            implementation(versionCatalog.lib("kermit"))
            implementation(versionCatalog.lib("kotlin-coroutines"))
            implementation(versionCatalog.lib("kotlin-datetime"))
            implementation(versionCatalog.lib("kotlin-serialization"))

            implementation(versionCatalog.lib("marp-core"))
            implementation(versionCatalog.lib("marp-core-data"))
            implementation(versionCatalog.lib("marp-core-db"))
            implementation(versionCatalog.lib("marp-core-ws"))
        }
    }
}