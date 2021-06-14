// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Dependencies.buildToolVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.gradleKotlinPlugin}")
    }
}
