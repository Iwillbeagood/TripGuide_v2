plugins {
    id("jun.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jun.tripguide_v2.feature.travel_init"
}

dependencies {
    implementation(libs.wheelPicker)
}