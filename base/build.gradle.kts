import com.shalan.dependencies_manager.DependenciesManager

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.shalan.dependencies")
}

android {
    buildFeatures{
        dataBinding = true
        buildConfig = true
    }
    compileSdkVersion(30)
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode(1)
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles.add(File("consumer-rules.pro"))
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
    implementation(DependenciesManager.VIEWMODEL)
    implementation(DependenciesManager.LIVEDATA)
    implementation(DependenciesManager.RECYCLER_VIEW)
    implementation(DependenciesManager.SWIPE_TO_REFRESH)
    kapt(DependenciesManager.LIFECYCLE_ANNOTATION_PROCESSOR)
    implementation(DependenciesManager.KOIN)
    implementation(DependenciesManager.KOIN_ANDROID)
    implementation(DependenciesManager.KOIN_ANDROID_SCOPE)
    implementation(DependenciesManager.KOIN_VIEWMODEL)
    implementation(DependenciesManager.RXJAVA_3)
    implementation(DependenciesManager.RX_KOTLIN_3)
    implementation(DependenciesManager.MOSHI)
    implementation(DependenciesManager.MOSHI_KOTLIN)
    kapt(DependenciesManager.MOSHI_KOTLIN_CODE_GENERATION)
    implementation(DependenciesManager.GLIDE)
    kapt(DependenciesManager.GLIDE_ANNOTATION_PROCESSOR)
    implementation(DependenciesManager.RETROFIT)
    debugImplementation(DependenciesManager.CHUCKER_DEBUG)
    releaseImplementation(DependenciesManager.CHUCKER_RELEASE)
    implementation(DependenciesManager.LOGGING_INTERCEPTOR)
    implementation(DependenciesManager.RX_JAVA3_ADAPTER)
}