import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.feature)
}

android {
    setNamespace("feature.travel_init")
}


dependencies {
    implementation(projects.feature.travelAddDialog)
    implementation(libs.wheelPicker)
    implementation(libs.kotlinx.immutable)
}