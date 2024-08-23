import com.jun.tripguide.setNamespace

plugins {
    alias(libs.plugins.jun.android.feature)
}

android {
    setNamespace("feature.main")
}

dependencies {
    implementation(projects.feature.mytravel)
    implementation(projects.feature.mytravelPlan)
    implementation(projects.feature.recommend)
    implementation(projects.feature.setting)
    implementation(projects.feature.travelInit)
    implementation(projects.feature.touristDetail)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.kakaoNavi)
}