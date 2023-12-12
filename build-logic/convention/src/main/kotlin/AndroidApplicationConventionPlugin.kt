import com.android.build.api.dsl.ApplicationExtension
import com.client.coincap.configureGradleManagedDevices
import com.client.coincap.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                // Access to the plugins - The plugin manager for this plugin aware object.
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                /**
                 ** Extension for the Android Gradle Plugin Application plugin
                 ** This is the android block when the com.android.application plugin is applied.
                 **/
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                configureGradleManagedDevices(this)
            }

            dependencies {
                // Dependencies for the Android Gradle Plugin Application plugin
            }
        }
    }
}
