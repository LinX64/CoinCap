plugins {
    id("coincap.android.library")
    id("coincap.kotlin.detekt")
}

android {
    namespace = "com.client.coincap.core.common"
}

dependencies {
    api(libs.kotlinx.coroutines.android)
}
