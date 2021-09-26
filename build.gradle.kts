// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven ("https://jitpack.io")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0-alpha08")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.gradleKotlinPlugin}")
    }
}
