import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.jun.android.application)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.jun.tripguide_v2"

    defaultConfig {
        applicationId = "com.jun.tripguide_v2"
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "KAKAO_NATIVE_KEY", getApiKey("kakao.native.key"))
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.feature.main)

    implementation(projects.core.designsystem)
    implementation(libs.kakaoNavi)
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}