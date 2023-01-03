plugins {
    id("coincap.android.library")
    id("coincap.kotlin.detekt")
    kotlin("kapt")
    id("coincap.android.hilt")
}

android {
    namespace = "com.client.coincap.core.domain"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))

    api(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
