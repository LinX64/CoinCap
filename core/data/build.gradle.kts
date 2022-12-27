plugins {
    id("coincap.android.library")
    id("coincap.kotlin.detekt")
    id("coincap.android.hilt")
}

android {
    namespace = "com.client.coincap.core.data"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    detekt {
        config = files("$rootDir/app/config/detekt/config.yml")
        parallel = true
        buildUponDefaultConfig = true
    }
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.retrofit.core)
    implementation(libs.gson.core)
    implementation(libs.gson.converter)
    implementation(libs.okhttp.logging)
}
