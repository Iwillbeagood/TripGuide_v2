import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.feature)
}

android {
    setNamespace("feature.travel-search")
}


dependencies {
    implementation(libs.kotlinx.immutable)
}