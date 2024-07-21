import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.feature)
}

android {
    setNamespace("feature.recommend")
}

dependencies {
    implementation(libs.play.services.location)
    implementation(libs.google.compose.maps)
    implementation(libs.accompanist.location)
}
