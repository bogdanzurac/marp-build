package dev.bogdanzurac.marp.build.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.configure
import java.net.URI
import java.util.*

class PublishingPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {

        with(pluginManager) {
            apply("maven-publish")
        }

        configure<PublishingExtension> {
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