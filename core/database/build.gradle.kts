plugins {
    id("jun.android.library")
    id("jun.android.hilt")
    id("jun.android.room")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jun.tripguide_v2_core.database"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.google.gson)
}