import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinDetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")
        }
    }
}
