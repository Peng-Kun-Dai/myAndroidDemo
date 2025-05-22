dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "myAndroidDemo"

include(
    ":DiceRoller",
    ":GreetingCard",
    ":HappyBirthday",
    ":TrainingTipCalculator"
)
project(":DiceRoller").projectDir = file("projects/DiceRoller")
project(":GreetingCard").projectDir = file("projects/GreetingCard")
project(":HappyBirthday").projectDir = file("projects/HappyBirthday")
project(":TrainingTipCalculator").projectDir = file("projects/TrainingTipCalculator")