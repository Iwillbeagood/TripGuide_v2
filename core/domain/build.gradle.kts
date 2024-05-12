import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("jun.android.library")
}

android {
    namespace = "com.jun.tripguide_v2.core.domain"

    defaultConfig {
        buildConfigField("String", "KAKAO_API_KEY", getApiKey("kakao.api.key"))
        buildConfigField("String", "KAKAO_NATIVE_KEY", getApiKey("kakao.native.key"))
        buildConfigField("String", "OPEN_API_KEY", getApiKey("open.api.key"))
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(libs.google.compose.maps)
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}