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

rootProject.name = "CoinCap"
include(":app")

// Core module
include(
    ":core:common",
    ":core:data",
    ":core:domain",
    ":core:ui"
)

// Ui module
include(
    ":ui:home",
    ":ui:search",
    ":ui:detail"
)