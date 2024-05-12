plugins {
    id("jun.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jun.tripguide_v2.feature.recommend"
}
dependencies {
    implementation(libs.play.services.location)
    implementation(libs.google.compose.maps)
    implementation(libs.accompanist.location)
}
