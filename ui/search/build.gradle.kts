plugins {
    id("coincap.android.library")
    id("coincap.android.library.compose")
    id("coincap.kotlin.detekt")
    id("coincap.android.hilt")
}

android {
    namespace = "com.client.coincap.ui.search"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
    implementation(project(":ui:detail"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
