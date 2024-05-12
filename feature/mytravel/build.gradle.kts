plugins {
    id("jun.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jun.tripguide_v2.feature.mytravel"
}

dependencies {
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.animation)
    implementation(libs.auto.image.slider)
}