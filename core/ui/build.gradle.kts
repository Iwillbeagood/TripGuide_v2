plugins {
    id("jun.android.library")
    id("jun.android.compose")
}

android {
    namespace = "com.jun.tripguide_v2.core.ui"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
}