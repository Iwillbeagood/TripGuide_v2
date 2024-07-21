import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.feature)
}

android {
    setNamespace("feature.travel_meansInfo")
}

dependencies {
    implementation(libs.wheelPicker)
}