import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.library)
    alias(libs.plugins.jun.android.room)
    alias(libs.plugins.jun.android.hilt)
}

android {
    setNamespace("core.database")
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.google.gson)
}