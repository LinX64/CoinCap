import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class KotlinDetektConventionPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        apply(plugin = "io.gitlab.arturbosch.detekt")
    }
}