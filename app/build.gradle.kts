plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "me.iamjoseph.raccoon"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
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

    implementation("com.github.iamjosephmj:Raccoon:${Dependencies.raccoon}")
    implementation("com.android.support:multidex:${Dependencies.multiDex}")

    implementation("androidx.core:core-ktx:${Dependencies.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Dependencies.appcompat}")
    implementation("com.google.android.material:material:${Dependencies.material}")
    implementation("androidx.constraintlayout:constraintlayout:${Dependencies.constraintlayout}")


    implementation("androidx.test.ext:junit-ktx:${Dependencies.junit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Dependencies.espresso}")
    androidTestImplementation("androidx.test:runner:${Dependencies.runner}")
    androidTestImplementation("androidx.test:rules:${Dependencies.rules}")

    implementation("com.squareup.retrofit2:retrofit:${Dependencies.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Dependencies.converter}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Dependencies.coroutines}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Dependencies.lifecycle}")


}