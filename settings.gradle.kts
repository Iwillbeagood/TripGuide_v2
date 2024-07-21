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
    ":core:domain",
    ":core:model",
    ":core:database",
    ":core:ui",

    ":feature:main",
    ":feature:mytravel",
    ":feature:mytravel-plan",
    ":feature:setting",
    ":feature:recommend",

    ":feature:travel-init",
    ":feature:travel-add-dialog",
    ":feature:travel-search"
)
include(":feature:tourist-detail")
include(":feature:travel-meansInfo")
include(":core:data-api")
include(":core:location")
