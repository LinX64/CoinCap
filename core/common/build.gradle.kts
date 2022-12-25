plugins {
    id("coincap.android.library")
    id("coincap.android.hilt")
}

android {
    namespace = "com.client.coincap.core.common"
}

dependencies {
    api(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}