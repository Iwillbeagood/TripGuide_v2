import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.feature)
}

android {
    setNamespace("feature.mytravel")
}


dependencies {
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.animation)
    implementation(libs.auto.image.slider)
}