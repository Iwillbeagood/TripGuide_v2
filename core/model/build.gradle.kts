import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.library)
    id("kotlinx-serialization")
}

android {
    setNamespace("core.model")
}

dependencies {
    api(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
}