plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id ("dagger.hilt.android.plugin")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.simpsons"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.simpsons"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Dagger Hitl
    implementation(libs.androidx.hitl.kotlin)

    kapt(libs.androidx.hitl.kpt)

    //LIVE DATA
    implementation(libs.androidx.live.data)

    //NAVIGATION
    implementation(libs.androidx.navigate.ui)
    implementation(libs.androidx.navigate.fragment)

    //GLIDE
    implementation(libs.androidx.glide)

    //CARDVIEW
    implementation(libs.androidx.card.view)

    //VIEW MODEL
    implementation(libs.androidx.view.model)

    //RETRFIT
    implementation(libs.androidx.retrofit)

    //GSON CONVERT
    implementation(libs.androidx.retrofit.gson)
    implementation(libs.androidx.retrofit.gson.conver)

    //LIFECYCLE
    implementation(libs.android.lifecycle.runtime)

    //CORRUTIENES
    implementation(libs.android.corrutienes)

    //ADS
    implementation(libs.android.ads)

    implementation(project(":base"))
    implementation(project(":network"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}