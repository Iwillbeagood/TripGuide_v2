import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.feature)
}

android {
    setNamespace("feature.tourist_detail")
}



dependencies {
    implementation(libs.google.compose.maps)
}