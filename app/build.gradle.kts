plugins {
    id("jun.android.application")
}

android {
    namespace = "com.jun.tripguide_v2"

    defaultConfig {
        applicationId = "com.jun.tripguide_v2"
        versionCode = 1
        versionName = "1.0"
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
}

dependencies {
    implementation(projects.feature.main)

    implementation(projects.core.designsystem)
}