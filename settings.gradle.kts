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
    ":feature:setting",
    ":feature:recommend",

    ":feature:addtravel",
    ":feature:travelroute",
    ":feature:addtourist",
)
