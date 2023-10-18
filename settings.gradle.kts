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
    }
}

rootProject.name = "TripGuide_v2"
include(
    ":app",

    ":core:designsystem",
    ":core:data",
    ":core:domain",
    ":core:navigation",
    ":core:model",
    ":core:datastore",
    ":core:ui",

    ":feature:main",
    ":feature:mytravel",
    ":feature:setting",
    ":feature:recommend",
    ":feature:addtravel"
)

include(":core:database")
