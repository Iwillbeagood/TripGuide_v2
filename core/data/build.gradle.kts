plugins {
    id("jun.android.library")
    id("jun.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.jun.tripguide_v2.core.data"
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