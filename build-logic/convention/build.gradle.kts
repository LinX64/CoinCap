import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.client.coincap.buildlogic"

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "coincap.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            id = "coincap.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "coincap.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "coincap.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = "coincap.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidTest") {
            id = "coincap.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }

        register("androidHilt") {
            id = "coincap.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("jvmLibrary") {
            id = "volution.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("kotlinDetekt") {
            id = "coincap.kotlin.detekt"
            implementationClass = "DetektConventionPlugin"
        }
    }
}
