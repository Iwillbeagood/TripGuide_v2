pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://devrepo.kakaomobility.com/repository/kakao-mobility-android-knsdk-public/") }
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "TripGuide_v2"
include(
    ":app",

    ":core:designsystem",
    ":core:data",
    ":core:data-api",
    ":core:domain",
    ":core:model",
    ":core:database",
    ":core:location",
    ":core:testing",
    ":core:ui",
    ":core:navigation",

    ":feature:main",
    ":feature:mytravel",
    ":feature:mytravel-plan",
    ":feature:setting",
    ":feature:recommend",

    ":feature:travel-init",
    ":feature:travel-add-dialog",
    ":feature:tourist-detail"
)
