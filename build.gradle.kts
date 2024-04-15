import java.io.IOException

tasks.register("clean") {
    gradle.includedBuilds.forEach {
        dependsOn(it.task(":clean"))
    }

    doFirst {
        if (!file("build").deleteRecursively())
            throw IOException("Failed to delete build directory!")
    }
}

tasks.register("build") {
    gradle.includedBuilds.forEach {
        if (it.name == "GuardianCommon") return@forEach

        dependsOn(it.task(":release"))
    }
}

tasks.register("test") {
    gradle.includedBuilds.forEach {
        dependsOn(it.task(":test"))
    }
}

tasks.register("spotlessApply") {
    gradle.includedBuilds.forEach {
        dependsOn(it.task(":spotlessApply"))
    }
}

tasks.register("spotlessCheck") {
    gradle.includedBuilds.forEach {
        dependsOn(it.task(":spotlessCheck"))
    }
}

tasks.register("publish") {
    gradle.includedBuilds.forEach {
        if (it.name == "GuardianCommon") return@forEach

        dependsOn(it.task(":publish"))
    }
}
