plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.globant"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(Libraries.lombok)
    annotationProcessor(Libraries.lombok)

    implementation(Libraries.springboot)
    implementation(Libraries.springbootWeb)
    implementation(Libraries.springbootValidation)

    // Testing
    testImplementation(Testing.springWebflux)
    testImplementation(Testing.springbootStarter)

    testRuntimeOnly(Testing.junit)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

tasks.named("bootJar") {
    enabled = false
}
