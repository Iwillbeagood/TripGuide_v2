import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.feature)
}

android {
    setNamespace("feature.mytravel-plan")
}

dependencies {
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.animation)
    implementation(libs.wheelPicker)
    implementation(libs.lazyListReorder)
    implementation(libs.kakaoNavi)

    implementation(libs.google.compose.maps)
}