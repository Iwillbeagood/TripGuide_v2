import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.feature)
}

android {
    setNamespace("feature.travel-init")
}


dependencies {
    implementation(libs.wheelPicker)
    implementation(libs.kotlinx.immutable)
}