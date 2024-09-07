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
    implementation(Libraries.springbootValidation)
    implementation(Libraries.springbootWeb)
    compileOnly(Libraries.lombok)
    annotationProcessor(Libraries.lombok)
    implementation(Libraries.springboot)
    implementation(Libraries.dataJpa)
    implementation(Libraries.postgresql)
    implementation(Libraries.springbootDevtools)
    implementation(Libraries.swagger)
    implementation(Libraries.springfox)




    testImplementation(Testing.springWebflux)
    testImplementation(Testing.springbootStarter)
    "developmentOnly"("org.springframework.boot:spring-boot-devtools")

    testRuntimeOnly(Testing.junit)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

