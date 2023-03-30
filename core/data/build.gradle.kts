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
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.retrofit.core)
    implementation(libs.gson.core)
    implementation(libs.gson.converter)
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit4)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.junit.jupiter)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kluent)
    testImplementation(libs.turbine)
}
