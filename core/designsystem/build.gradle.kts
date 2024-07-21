import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.library)
    alias(libs.plugins.jun.android.compose)
}

android {
    setNamespace("core.designsystem")
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.androidx.appcompat)
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.animation)
    implementation(libs.coil.compose)
    implementation(libs.coil.gif)

    implementation(libs.wheelPicker)
    implementation(libs.auto.image.slider)
}