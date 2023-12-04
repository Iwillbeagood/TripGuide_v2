plugins {
    id("jun.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jun.tripguide_v2.feature.mytravel.plan"
}

dependencies {
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.animation)
    implementation(libs.wheelPicker)
    implementation(libs.lazyListReorder)
    implementation(libs.kakaoNavi)

    implementation(libs.google.compose.maps)
}