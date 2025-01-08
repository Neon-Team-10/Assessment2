plugins {
    id("java-library")
    id("jacoco")
    id("com.gradleup.shadow") version "8.3.3"
}

group = "io.github.neonteam10"
version = "2.0"

repositories {
    mavenCentral()
}

dependencies {
    api("com.badlogicgames.gdx:gdx:1.12.1")
    api("com.badlogicgames.gdx:gdx-freetype:1.12.1")
    implementation("com.badlogicgames.gdx:gdx-backend-lwjgl3:1.12.1")
    implementation("com.badlogicgames.gdx:gdx-platform:1.12.1:natives-desktop")
    implementation("com.badlogicgames.gdx:gdx-backend-headless:1.10.0")
    implementation("com.badlogicgames.gdx:gdx-freetype-platform:1.12.1:natives-desktop")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter") 

    testImplementation("org.mockito:mockito-core:4.0.0")
    
        //testRuntimeOnly("org.junit.platform:junit-platform-engine")
}
jacoco {
    toolVersion = "0.8.12"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}
tasks.compileJava {
    options.release = 17;
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "io.github.uoyteamsix.UniSimGame"
        )
    }
}

tasks.shadowJar {
    minimize()
}
tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = false
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}

