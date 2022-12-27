plugins {
    id("coincap.android.library")
    id("coincap.kotlin.detekt")
}

android {
    namespace = "com.client.coincap.core.common"

    detekt {
        config = files("$rootDir/app/config/detekt/config.yml")
        parallel = true
        buildUponDefaultConfig = true
        autoCorrect = true
    }
}

dependencies {
    api(libs.kotlinx.coroutines.android)
}
