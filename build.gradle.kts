plugins {
	java
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example.demo"
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
    implementation("org.springframework:spring-web")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.3.2")
    implementation("org.liquibase:liquibase-core:4.26.0")
    implementation("com.mysql:mysql-connector-j:9.0.0")
    implementation("org.yaml:snakeyaml:2.2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
