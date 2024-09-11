plugins {
    id("java")
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
    implementation(project(":common"))

    implementation(Libraries.springbootWeb)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation(Libraries.springbootValidation)

    runtimeOnly("org.postgresql:postgresql:42.7.2")

    compileOnly(Libraries.lombok)
    annotationProcessor(Libraries.lombok)
    annotationProcessor(Libraries.mapStructProcessor)
    implementation(Libraries.mapStruct)

    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly(Testing.junit)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

springBoot {
    mainClass.set("com.globant.apigateway.ApiGatewayApplication")
}