import org.apache.commons.io.output.ByteArrayOutputStream
import java.nio.charset.Charset

plugins {
    kotlin("jvm")
    id("fabric-loom")
    id("io.github.juuxel.loom-vineflower").version("1.11.0")
    `maven-publish`
    java
}

group = property("maven_group")!!
version = this.getGitHash().substring(0, 6)

repositories {
    mavenLocal()
    maven {
        url = uri("https://maven.parchmentmc.org/")
    }
    maven {
        url = uri("https://masa.dy.fi/maven")
    }
    maven {
        url = uri("https://jitpack.io")
    }
    maven {
        url = uri("https://ueaj.dev/maven")
    }
    maven {
        url = uri("https://maven.nucleoid.xyz")
    }
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${property("parchment_version")}@zip")
    })

    include(modImplementation("com.github.CasualChampionships:arcade:${property("arcade_version")}")!!)

    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")

    include(modImplementation("xyz.nucleoid:server-translations-api:${property("server_translations_api_version")}")!!)
    include(modImplementation("xyz.nucleoid:fantasy:${property("fantasy_version")}")!!)
    include(modImplementation("com.github.Senseiwells:ServerReplay:${property("server_replay_version")}")!!)
    include(modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")!!)

    include(modImplementation("eu.pb4:polymer-core:${property("polymer_version")}")!!)
    include(modImplementation("eu.pb4:polymer-blocks:${property("polymer_version")}")!!)
    include(modImplementation("eu.pb4:polymer-resource-pack:${property("polymer_version")}")!!)
    include(modImplementation("eu.pb4:polymer-virtual-entity:${property("polymer_version")}")!!)

    include(modImplementation("com.github.ReplayMod:ReplayStudio:a1e2b83") {
        exclude(group = "org.slf4j")
        exclude(group = "com.google.guava", module = "guava-jdk5")
        exclude(group = "com.google.guava", module = "guava")
        exclude(group = "com.google.code.gson", module = "gson")
    })

    include(implementation("org.mongodb:mongo-java-driver:3.12.11")!!)
    // include(implementation("org.java-websocket:Java-WebSocket:1.5.3")!!)

    include(implementation(annotationProcessor("com.github.llamalad7.mixinextras:mixinextras-fabric:${property("mixin_extras_version")}")!!)!!)

    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    loom {
        accessWidenerPath.set(file("src/main/resources/uhc.accesswidener"))
    }

    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand(mutableMapOf("version" to project.version))
        }
    }

    jar {
        from("LICENSE")
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                artifact(remapJar) {
                    builtBy(remapJar)
                }
                artifact(kotlinSourcesJar) {
                    builtBy(remapSourcesJar)
                }
            }
        }

        repositories {

        }
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}

java {
    withSourcesJar()
}

fun getGitHash(): String {
    val out = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "HEAD")
        standardOutput = out
    }
    return out.toString(Charset.defaultCharset()).trim()
}