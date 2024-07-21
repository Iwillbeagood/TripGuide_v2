import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.library)
}

android {
    setNamespace("core.data_api")
}

dependencies {
    implementation(projects.core.model)
}