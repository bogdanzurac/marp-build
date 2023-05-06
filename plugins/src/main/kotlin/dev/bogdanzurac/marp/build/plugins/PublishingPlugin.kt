package dev.bogdanzurac.marp.build.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import java.net.URI
import java.util.*

class PublishingPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {

        with(pluginManager) {
            apply("com.android.library")
            apply("maven-publish")
        }

        android {
            publishing {
                singleVariant("release") {
                    withSourcesJar()
                    withJavadocJar()
                }
            }
        }

        configure<PublishingExtension> {
            publications {
                create<MavenPublication>("publishArtifact") {
                    afterEvaluate {
                        from(components.findByName("release"))
                    }
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
    }
}