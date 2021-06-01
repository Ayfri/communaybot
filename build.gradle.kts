import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
val kordexVersion: String by project

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
}



tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKT")
}
