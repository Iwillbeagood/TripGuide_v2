import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.library)
    alias(libs.plugins.jun.android.compose)
}

android {
    setNamespace("core.ui")
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
}