import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.charset.StandardCharsets

plugins {
    java
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.lombok)
    alias(libs.plugins.honoka.basic)
}

group = "de.honoka.test"
version = libs.versions.p.root.get()

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

honoka.basic {
    dependencies {
        lombok()
        kotlin()
    }
}

dependencies {
    implementation(libs.honoka.kotlin.utils)
    implementation("org.junit.jupiter:junit-jupiter:5.12.2")
}

tasks {
    withType<JavaCompile> {
        options.run {
            encoding = StandardCharsets.UTF_8.name()
            val compilerArgs = compilerArgs as MutableCollection<String>
            compilerArgs += listOf("-parameters")
        }
    }

    /*
     * 由于除了原本的compileKotlin任务外，还存在compileTestKotlin和kapt的KaptGenerateStubsTask
     * （KotlinCompile的子类）任务需要配置，因此这里不能使用“compileKotlin {}”块。
     */
    withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict", "-Xjvm-default=all")
        }
    }

    withType<Test> {
        useJUnitPlatform()
        workingDir = rootDir
    }
}

kapt {
    keepJavacAnnotationProcessors = true
}

libs.versions.d.kotlin.coroutines
libs.versions.d.lombok
