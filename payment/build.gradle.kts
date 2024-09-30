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

    implementation(Libraries.springboot)
    implementation(Libraries.swagger)

    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.mockito:mockito-core")
    testImplementation("com.jayway.jsonpath:json-path")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly(Testing.junit)
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

springBoot {
    mainClass.set("com.globant.apigateway.ApiGatewayApplication")
}