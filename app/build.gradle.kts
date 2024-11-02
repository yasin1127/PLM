

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id ("kotlin-kapt")


}

android {
    namespace = "com.example.personallibraryapp"
    compileSdk = 34
    buildFeatures.dataBinding=true
    buildFeatures.viewBinding=true
        buildToolsVersion ="34.0.0" // Update to the latest


    defaultConfig {
        applicationId = "com.example.personallibraryapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}
buildscript {
   // kotlin_version = "1.7.10" // Check for the latest version
    repositories {
        google()
        mavenCentral()
    }}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    ksp ("androidx.room:room-compiler:2.5.0")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("androidx.room:room-runtime:2.5.0")
    implementation ("androidx.room:room-ktx:2.5.0")
  // For Kotlin projects
    implementation ("androidx.recyclerview:recyclerview:1.3.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation ("androidx.databinding:databinding-runtime:7.4.0")

    //classpath ("com.android.tools.build:gradle:8.1.0") // Update to the latest version
    //classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")

}