import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.library)
    alias(libs.plugins.jun.android.hilt)
    id("kotlinx-serialization")
}

android {
    setNamespace("core.data")

    defaultConfig {
        buildConfigField("String", "KAKAO_API_KEY", getApiKey("kakao.api.key"))
        buildConfigField("String", "OPEN_API_KEY", getApiKey("tour.api.key"))
        buildConfigField("String", "KAKAO_NATIVE_KEY", getApiKey("kakao.native.key"))
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.database)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    implementation(libs.google.compose.maps)
    implementation(libs.play.services.location)
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}