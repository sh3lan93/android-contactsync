import com.shalan.dependencies_manager.DependenciesManager

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("com.shalan.dependencies")
}

android {
    buildFeatures {
        dataBinding = true
    }
    compileSdkVersion(30)
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.shalan.contactssync"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode(1)
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
    implementation(DependenciesManager.CORE_KTX)
    implementation(DependenciesManager.APP_COMPACT)
    implementation(DependenciesManager.MATERIAL_DESIGN)
    implementation(DependenciesManager.CONSTRAINTLAYOUT)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation(DependenciesManager.JUNIT)
    androidTestImplementation(DependenciesManager.ANDROID_TEST_EXT)
    androidTestImplementation(DependenciesManager.ESPRESSO)
    implementation(project(":base"))
    implementation(DependenciesManager.RXJAVA_3)
    implementation(DependenciesManager.ROOM_DATABASE)
    kapt(DependenciesManager.ROOM_ANNOTATION_PROCESSOR)
    implementation(DependenciesManager.ROOM_RXJAVA_3_SUPPORT)
    implementation(DependenciesManager.VIEWMODEL)
    implementation(DependenciesManager.LIVEDATA)
    implementation(DependenciesManager.KOIN)
    implementation(DependenciesManager.KOIN_VIEWMODEL)
    implementation(DependenciesManager.KOIN_ANDROID)
    implementation(DependenciesManager.KOIN_ANDROID_SCOPE)
    implementation(DependenciesManager.RX_ANDROID_3)
    implementation(DependenciesManager.MOSHI)
    implementation(DependenciesManager.MOSHI_KOTLIN)
    kapt(DependenciesManager.MOSHI_KOTLIN_CODE_GENERATION)
    implementation(DependenciesManager.NAVIGATION_COMPONENT)
    implementation(DependenciesManager.NAVIGATION_COMPONENT_UI_KTX)
    implementation(DependenciesManager.WORKERMANAGER)
    implementation(DependenciesManager.WORKERMANAGER_RX_JAVA2)

}