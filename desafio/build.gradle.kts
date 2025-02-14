plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.itau"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
