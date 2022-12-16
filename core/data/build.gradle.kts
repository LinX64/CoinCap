plugins {
    id("coincap.android.library")
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
    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.android)
}