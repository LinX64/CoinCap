plugins {
    id("coincap.android.library")
    id("coincap.android.hilt")
}

android {
    namespace = "com.client.coincap.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}