import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinDetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            dependencies {
                add("detektPlugins", "io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")
            }
        }
    }
}
