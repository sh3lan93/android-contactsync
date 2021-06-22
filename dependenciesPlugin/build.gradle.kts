plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("dependencies"){
            id = "com.shalan.dependencies"
            implementationClass = "com.shalan.dependencies_manager.DependenciesManager"
        }
    }
}