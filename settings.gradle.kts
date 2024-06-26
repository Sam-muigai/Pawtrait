pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven { url = uri("https://androidx.dev/snapshots/builds/11670047/artifacts/repository/") }
    }
}

rootProject.name = "Pawtrait"
include(":app")
