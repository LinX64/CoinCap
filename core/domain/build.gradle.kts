plugins {
    id("coincap.android.library")
    kotlin("kapt")
}

android {
    namespace = "com.client.coincap.core.domain"
}

dependencies {
    implementation(project(":core:data"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}