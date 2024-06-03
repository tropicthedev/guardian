plugins {
    id("java")
}

group = "com.tropicoss"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.dv8tion:JDA:5.0.0-beta.22") {
        exclude(group = "club.minnced", module = "opus-java")
    }
    implementation("com.sparkjava:spark-core:2.9.4")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.tropicoss:GuardianCommon")
}

tasks.test {
    useJUnitPlatform()
}