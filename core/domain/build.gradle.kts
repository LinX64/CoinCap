plugins {
    id("coincap.android.library")
    id("coincap.kotlin.detekt")
    kotlin("kapt")
}

android {
    namespace = "com.client.coincap.core.domain"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}