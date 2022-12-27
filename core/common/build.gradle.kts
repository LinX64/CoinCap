plugins {
    id("coincap.android.library")
}

android {
    namespace = "com.client.coincap.core.common"
}

dependencies {
    api(libs.kotlinx.coroutines.android)
}