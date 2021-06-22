package com.shalan.dependencies_manager

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by Mohamed Shalan on 08/06/2021
 */

class DependenciesManager : Plugin<Project> {

    companion object {

        val GRADLE_CLASSPATH = "com.android.tools.build:gradle:${VersionsManager.GRADLE_VERSION}"

        val APP_COMPACT = "androidx.appcompat:appcompat:${VersionsManager.APP_COMPACT}"

        val CORE_KTX = "androidx.core:core-ktx:${VersionsManager.CORE_KTX}"

        val MATERIAL_DESIGN =
            "com.google.android.material:material:${VersionsManager.MATERIAL_DESIGN}"

        val JUNIT = "junit:junit:${VersionsManager.JUNIT}"

        val ANDROID_TEST_EXT = "androidx.test.ext:junit:${VersionsManager.ANDROID_TEST_EXT}"

        val ESPRESSO = "androidx.test.espresso:espresso-core:${VersionsManager.ESPRESSO}"

        val FIREBASE_BOM = "com.google.firebase:firebase-bom:${VersionsManager.FIREBASE_BOM}"

        val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"

        val APOLLO_CLIENT =
            "com.apollographql.apollo:apollo-runtime:${VersionsManager.APOLLO_CLIENT}"

        val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor"

        val APOLLO_RXJAVA_3 =
            "com.apollographql.apollo:apollo-rx3-support:${VersionsManager.APOLLO_CLIENT}"

        val RXJAVA_3 = "io.reactivex.rxjava3:rxjava:${VersionsManager.RXJAVA_3}"

        val KOIN = "io.insert-koin:koin-core:${VersionsManager.KOIN}"

        val KOIN_ANDROID = "io.insert-koin:koin-android:${VersionsManager.KOIN}"

        val KOIN_ANDROID_SCOPE = "io.insert-koin:koin-androidx-scope:${VersionsManager.KOIN}"

        val KOIN_VIEWMODEL = "io.insert-koin:koin-androidx-viewmodel:${VersionsManager.KOIN}"

        val OKHTTP_BOM = "com.squareup.okhttp3:okhttp-bom:${VersionsManager.OKHTTP_BOM}"

        val OKHTTP = "com.squareup.okhttp3:okhttp"

        val MOSHI = "com.squareup.moshi:moshi:${VersionsManager.MOSHI}"

        val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${VersionsManager.MOSHI}"

        val MOSHI_KOTLIN_CODE_GENERATION =
            "com.squareup.moshi:moshi-kotlin-codegen:${VersionsManager.MOSHI}"

        val RX_KOTLIN_3 = "io.reactivex.rxjava3:rxkotlin:${VersionsManager.RX_KOTLIN_3}"

        val MATERIAL_DIALOG =
            "com.afollestad.material-dialogs:core:${VersionsManager.MATERIAL_DIALOG}"

        val RX_ANDROID_2 = "io.reactivex.rxjava2:rxandroid:${VersionsManager.RX_ANDROID_2}"

        val RX_ANDROID_3 = "io.reactivex.rxjava3:rxandroid:${VersionsManager.RX_ANDROID_3}"

        val RX_JAVA_2 = "io.reactivex.rxjava2:rxjava:${VersionsManager.RX_JAVA_2}"

        val MOCKITO = "org.mockito:mockito-core:${VersionsManager.MOCKITO}"

        val ANDROIDX_ANNOTATION =
            "androidx.annotation:annotation:${VersionsManager.ANDROIDX_ANNOTATION}"

        val DATE4J = "com.darwinsys:hirondelle-date4j:${VersionsManager.DATE4J}"

        val SWIPE_TO_REFRESH =
            "androidx.swiperefreshlayout:swiperefreshlayout:${VersionsManager.SWIPE_TO_REFRESH}"

        val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${VersionsManager.LIFECYCLE}"

        val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${VersionsManager.LIFECYCLE}"

        val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${VersionsManager.FRAGMENT_KTX}"

        val RX_PREFERENCES =
            "com.f2prateek.rx.preferences2:rx-preferences:${VersionsManager.RX_PREFERENCES}"

        val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${VersionsManager.RECYCLER_VIEW}"

        val RX_KOTLIN2 = "io.reactivex.rxjava2:rxkotlin:${VersionsManager.RX_KOTLIN2}"

        val LIFECYCLE_ANNOTATION_PROCESSOR =
            "androidx.lifecycle:lifecycle-compiler:${VersionsManager.LIFECYCLE}"

        val GLIDE = "com.github.bumptech.glide:glide:${VersionsManager.GLIDE}"

        val GLIDE_ANNOTATION_PROCESSOR =
            "com.github.bumptech.glide:compiler:${VersionsManager.GLIDE}"

        val RETROFIT = "com.squareup.retrofit2:retrofit:${VersionsManager.RETROFIT}"

        val LOGGING_INTERCEPTOR =
            "com.github.ihsanbal:LoggingInterceptor:${VersionsManager.LOGGING_INTERCEPTOR}"

        val CHUCKER_DEBUG = "com.github.chuckerteam.chucker:library:${VersionsManager.CHUCKER}"

        val CHUCKER_RELEASE = "com.github.chuckerteam.chucker:library:${VersionsManager.CHUCKER}"

        val RX_JAVA3_ADAPTER =
            "com.github.akarnokd:rxjava3-retrofit-adapter:${VersionsManager.RX_JAVA3_ADAPTER}"

        val CONSTRAINTLAYOUT =
            "androidx.constraintlayout:constraintlayout:${VersionsManager.CONSTRAINTLAYOUT}"

        val ROOM_DATABASE = "androidx.room:room-runtime:${VersionsManager.ROOM}"

        val ROOM_ANNOTATION_PROCESSOR = "androidx.room:room-compiler:${VersionsManager.ROOM}"

        val ROOM_RXJAVA_3_SUPPORT = "androidx.room:room-rxjava3:${VersionsManager.ROOM}"

        val NAVIGATION_COMPONENT =
            "androidx.navigation:navigation-fragment-ktx:${VersionsManager.NAVIGATION_COMPONENT}"

        val NAVIGATION_COMPONENT_UI_KTX =
            "androidx.navigation:navigation-ui-ktx:${VersionsManager.NAVIGATION_COMPONENT}"

        val WORKERMANAGER = "androidx.work:work-runtime-ktx:${VersionsManager.WORKERMANAGER}"

        val WORKERMANAGER_RX_JAVA2 = "androidx.work:work-rxjava2:${VersionsManager.WORKERMANAGER}"

    }

    override fun apply(target: Project) {

    }


}