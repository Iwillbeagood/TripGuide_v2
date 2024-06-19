import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("jun.android.feature")
}

android {
    namespace = "com.jun.tripguide_v2.feature.main"
}

dependencies {
    implementation(projects.feature.mytravel)
    implementation(projects.feature.mytravelPlan)
    implementation(projects.feature.recommend)
    implementation(projects.feature.setting)
    implementation(projects.feature.travelInit)
    implementation(projects.feature.travelMeansInfo)
    implementation(projects.feature.travelAddDialog)
    implementation(projects.feature.travelSearch)
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