plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.animeapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.animeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.converter.gson) // For JSON parsing
    implementation(libs.logging.interceptor) // Optional: Logging
    implementation(libs.kotlinx.coroutines.android) // For Android
    implementation(libs.kotlinx.coroutines.core) // For Coroutines
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // For ViewModel + Coroutines
    implementation(libs.glide) // Glide dependency
    annotationProcessor(libs.compiler) // Glide annotation processor
    implementation(libs.lottie)

}