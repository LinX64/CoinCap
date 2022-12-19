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

include(":core:common", ":core:data", ":core:database")
include(":core:data")
include(":core:domain")
include(":core:ui")
include("")

include(":ui:home")
include(":ui:search")
