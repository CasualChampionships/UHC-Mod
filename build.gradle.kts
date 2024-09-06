import org.apache.commons.io.output.ByteArrayOutputStream
import java.nio.charset.Charset

plugins {
    val jvmVersion = libs.versions.fabric.kotlin.get()
        .split("+kotlin.")[1]
        .split("+")[0]

    kotlin("jvm").version(jvmVersion)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.fabric.loom)
    `maven-publish`
    java
}

group = "net.casual"
version = this.getGitHash().substring(0, 6)

allprojects {
    apply(plugin = "fabric-loom")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

    repositories {
        mavenLocal()
        maven("https://maven.maxhenkel.de/repository/public")
        maven("https://maven.parchmentmc.org/")
        maven("https://jitpack.io")
        maven("https://maven.nucleoid.xyz")
        maven("https://repo.fruxz.dev/releases/")
        mavenCentral()
    }

    configurations.all {
        // This is to resolve any conflicts with arcade-datagen
        resolutionStrategy {
            force(rootProject.libs.arcade)
        }
    }

    dependencies {
        val libs = rootProject.libs

        minecraft(libs.minecraft)
        @Suppress("UnstableApiUsage")
        mappings(loom.layered {
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-${libs.versions.parchment.get()}@zip")
        })
        modImplementation(libs.fabric.loader)
        modImplementation(libs.fabric.api)
        modImplementation(libs.fabric.kotlin)

        modImplementation(libs.arcade)
        modImplementation(libs.arcade.datagen)
        modImplementation(libs.server.replay)

        modImplementation(libs.map.canvas)
    }

    java {
        withSourcesJar()
    }

    loom {
        runs {
            create("datagenClient") {
                client()
                programArgs("--arcade-datagen")
                runDir = "run-datagen"
            }
        }
    }

    tasks {
        processResources {
            inputs.property("version", version)
            filesMatching("fabric.mod.json") {
                expand(mutableMapOf("version" to version))
            }
        }

        jar {
            from("LICENSE")
        }
    }
}

dependencies {
    include(libs.arcade)
    include(libs.server.replay)
    include(libs.map.canvas)
    includeModImplementation(libs.glide)

    for (subproject in project.subprojects) {
        if (subproject.path != ":minigames") {
            implementation(project(mapOf("path" to subproject.path, "configuration" to "namedElements")))
            include(subproject)
        }
    }

    includeImplementation(libs.casual.database)
}

fun getGitHash(): String {
    val out = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "HEAD")
        standardOutput = out
    }
    return out.toString(Charset.defaultCharset()).trim()
}

private fun DependencyHandler.includeModImplementation(dependencyNotation: Any) {
    include(dependencyNotation)
    modImplementation(dependencyNotation)
}

private fun DependencyHandler.includeImplementation(dependencyNotation: Any) {
    include(dependencyNotation)
    implementation(dependencyNotation)
}