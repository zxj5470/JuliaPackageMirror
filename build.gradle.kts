import netflix.nebula.NebulaKotlinPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

group = "com.github.zxj5470"
version = "1.0-SNAPSHOT"

buildscript {
	var kotlin_version: String by extra
	kotlin_version = "1.2.10"

	repositories {
		mavenCentral()
	}

	dependencies {
		classpath(kotlinModule("gradle-plugin", kotlin_version))
		classpath("com.netflix.nebula:nebula-kotlin-plugin:1.2.21")
	}

}

apply {
	plugin("java")
	plugin("kotlin")
}
plugins {
	application
}

val kotlin_version: String by extra

repositories {
	mavenCentral()
}

dependencies {
	compile(kotlinModule("stdlib-jdk8", kotlin_version))
	compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:0.22.3")
	compile("org.eclipse.jgit:org.eclipse.jgit:+")
	testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

configure<KotlinProjectExtension> {
	experimental.coroutines = Coroutines.ENABLE
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}
