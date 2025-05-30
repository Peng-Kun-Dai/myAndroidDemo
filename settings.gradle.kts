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
    ":TipCalculator",
    ":Affirmations",
    ":TrainingCourses",
    ":TrainingWoof",
    ":Superheroes",
)
project(":DiceRoller").projectDir = file("projects/DiceRoller")
project(":GreetingCard").projectDir = file("projects/GreetingCard")
project(":HappyBirthday").projectDir = file("projects/HappyBirthday")
project(":TipCalculator").projectDir = file("projects/TrainingTipCalculator")
project(":Affirmations").projectDir = file("projects/Affirmations")
project(":TrainingCourses").projectDir = file("projects/TrainingCourses")
project(":TrainingWoof").projectDir = file("projects/TrainingWoof")
project(":Superheroes").projectDir = file("projects/Superheroes")