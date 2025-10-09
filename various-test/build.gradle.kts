import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.charset.StandardCharsets

plugins {
    java
    `java-library`
    `maven-publish`
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.lombok)
    alias(libs.plugins.honoka.basic)
}

group = "de.honoka.test"
version = libs.versions.p.root.get()

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

dependencies {
    implementation(libs.honoka.kotlin.utils)
    implementation("net.java.dev.jna:jna-platform:5.13.0")
    implementation("com.sobte.cqp:jcq:1.2.7")
    implementation("com.baomidou:mybatis-plus:3.5.2")
    //Test
    implementation("junit:junit:4.13")
}

honoka.basic {
    dependencies {
        kotlin()
        lombok()
    }
}

tasks {
    withType<JavaCompile> {
        options.run {
            encoding = StandardCharsets.UTF_8.name()
            val compilerArgs = compilerArgs as MutableCollection<String>
            compilerArgs += listOf("-parameters")
        }
    }

    withType<KotlinCompile> {
        withType<KotlinCompile> {
            compilerOptions {
                freeCompilerArgs.addAll("-Xjsr305=strict", "-Xjvm-default=all")
            }
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

libs.versions.d.kotlin.coroutines
libs.versions.d.lombok
