import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.feature)
}

android {
    setNamespace("feature.tourist-detail")
}



dependencies {
    implementation(libs.google.compose.maps)
}