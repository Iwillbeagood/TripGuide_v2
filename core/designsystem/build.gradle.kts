plugins {
    id("jun.android.library")
    id("jun.android.compose")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jun.tripguide_v2.core.designsystem"
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