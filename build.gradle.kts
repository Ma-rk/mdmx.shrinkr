import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.6.2"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  kotlin("jvm") version "1.6.10"
  kotlin("plugin.spring") version "1.6.10"

  // https://velog.io/@conatuseus/작성중Kotlin-JPA를-사용하며-만난-문제
  // https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.jpa
  // 모델 클래스에 기본 생성자를 추가하기 위해 사용
  kotlin("plugin.jpa") version "1.3.72"

  // https://www.bsidesoft.com/7871
  // https://github.com/GoogleContainerTools/jib
  // jib 빌드로 도커 이미지를 생성하는 플러그인을 추가하기 위해 사용
  id("com.google.cloud.tools.jib") version "3.2.0"
}

group = "com.mdmx"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
  mavenCentral()
}

dependencies {
  // https://spring.io/projects/spring-boot
  implementation("org.springframework.boot:spring-boot-starter:2.6.2")

  // https://kotlinlang.org/
  implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")

  // https://kotlinlang.org/
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.10")

  // https://spring.io/projects/spring-boot
  testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.2")

  // https://github.com/MicroUtils/kotlin-logging
  implementation("io.github.microutils:kotlin-logging-jvm:2.1.20")

  // https://spring.io/projects/spring-boot
  implementation("org.springframework.boot:spring-boot-starter-web:2.6.2")

  // https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui
  implementation("io.springfox:springfox-boot-starter:3.0.0")
  implementation("io.springfox:springfox-swagger-ui:2.9.2")

  // https://dev.mysql.com/doc/connector-j/8.0/en/
  runtimeOnly("mysql:mysql-connector-java:8.0.25")

  // https://spring.io/projects/spring-data-jpa
  implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.2")

  // https://hashids.org/java/
  implementation("org.hashids:hashids:1.0.3")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

jib {
  from {
    image = "openjdk:17-alpine"
  }
  to {
    image = "shirinkr"
    tags = setOf("0.0.1")
  }
  container {
    jvmFlags = listOf("-Xms128m", "-Xmx128m", "-Dspring.profiles.active=prod")
  }
}