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
    maven { url = uri("https://jitpack.io") }
}

dependencies {

    implementation(project(":common"))

    compileOnly(Libraries.lombok)
    annotationProcessor(Libraries.lombok)
    annotationProcessor(Libraries.mapStructProcessor)
    annotationProcessor(Libraries.vavrMatchProcessor)

    implementation("org.apache.pdfbox:pdfbox:2.0.28")

    implementation(Libraries.springboot)
    implementation(Libraries.springbootWeb)
    implementation(Libraries.springbootValidation)
    implementation(Libraries.springbootMongo)
    implementation(Libraries.mapStruct)
    implementation(Libraries.vavr)
    implementation(Libraries.varvMatch)
    implementation(Libraries.swagger)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    implementation(Libraries.qrGenJava)

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.mockito:mockito-core")
    testImplementation("com.jayway.jsonpath:json-path")
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

