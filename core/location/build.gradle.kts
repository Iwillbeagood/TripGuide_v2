import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.library)
}

android {
    setNamespace("core.location")
}

dependencies {
    implementation(libs.google.compose.maps)
    implementation(libs.play.services.location)
    testImplementation(libs.androidx.core.testing)
    testImplementation(projects.core.testing)
}