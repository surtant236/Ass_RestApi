plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.ass_restapi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ass_restapi"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

// Gson converter
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

// OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

// Logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))

// Firebase Authentication
    implementation("com.google.firebase:firebase-auth")

// Firestore
    implementation("com.google.firebase:firebase-firestore")

// Realtime Database
    implementation("com.google.firebase:firebase-database")

// Firebase Storage
    implementation("com.google.firebase:firebase-storage")

// Firebase Analytics
    implementation("com.google.firebase:firebase-analytics")


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
    }
}
