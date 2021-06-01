import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
val kordexVersion: String by project
val logbackVersion: String by project
val kotlinLoggingVersion: String by project

plugins {
    kotlin("jvm") version "1.4.32"
    application
}

group = "fr.ayfri"
version = "0.1"

repositories {
    maven {
        name = "Kotlin Discord"
        url = uri("https://maven.kotlindiscord.com/repository/maven-public/")
    }
}


dependencies {
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:$kordexVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
}



tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKT")
}
