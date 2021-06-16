plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    `maven-publish`
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

    implementation("com.android.support:multidex:${Dependencies.multiDex}")
    implementation("androidx.core:core-ktx:${Dependencies.coreKtx}")
    implementation("com.squareup.okhttp3:okhttp:${Dependencies.okHttp}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Dependencies.reflect}")
    implementation("com.squareup.retrofit2:converter-gson:${Dependencies.gson}")
    implementation("com.squareup.moshi:moshi:${Dependencies.moshi}")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:${Dependencies.moshi}")
    implementation("com.squareup.moshi:moshi-kotlin:${Dependencies.moshi}")
    testImplementation("junit:junit:${Dependencies.raccoonJunit}")

}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("raccoon") {
                groupId = "com.github.iamjosephmj"
                artifactId = "raccoon"
                version = "1.0.2"
            }
        }
    }
}
