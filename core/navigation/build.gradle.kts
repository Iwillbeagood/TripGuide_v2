import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.library)
    alias(libs.plugins.jun.android.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    setNamespace("navigation")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}