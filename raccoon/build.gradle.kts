import com.android.builder.dexing.isProguardRule

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk = 21
        targetSdk = 30
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    implementation("androidx.core:core-ktx:${Dependencies.coreKtx}")
    implementation("com.squareup.okhttp3:okhttp:${Dependencies.okHttp}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Dependencies.reflect}")
    implementation("com.squareup.retrofit2:converter-gson:${Dependencies.gson}")
    implementation("com.squareup.moshi:moshi:${Dependencies.moshi}")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:${Dependencies.moshi}")
    implementation("com.squareup.moshi:moshi-kotlin:${Dependencies.moshi}")

    testImplementation("junit:junit:4.13.2")

}