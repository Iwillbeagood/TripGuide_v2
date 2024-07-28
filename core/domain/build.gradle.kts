import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.library)
}

android {
    setNamespace("core.domain")
}

dependencies {
    implementation(projects.core.dataApi)
    implementation(projects.core.model)
    implementation(projects.core.location)
    implementation(libs.google.compose.maps)
    implementation(libs.inject)
}
