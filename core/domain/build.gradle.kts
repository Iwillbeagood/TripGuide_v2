import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("jun.android.library")
}

android {
    namespace = "com.jun.tripguide_v2.core.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(libs.google.compose.maps)
}
