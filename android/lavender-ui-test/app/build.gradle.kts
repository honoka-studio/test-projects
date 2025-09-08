import de.honoka.gradle.plugin.android.ext.kotlinAndroid

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.honoka.android)
}

version = libs.versions.p.app.get()

java {
    toolchain.languageVersion = JavaLanguageVersion.of(11)
}

android {
    namespace = "de.honoka.lavender.android.uitest"
    compileSdk = libs.versions.a.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = "de.honoka.lavender.android.uitest"
        minSdk = libs.versions.a.min.sdk.get().toInt()
        targetSdk = compileSdk
        versionCode = libs.versions.pvc.app.get().toInt()
        versionName = project.version as String
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        val excludes = listOf("META-INF/INDEX.LIST")
        resources.excludes.addAll(excludes)
    }

    buildFeatures {
        compose = true
    }
}

//noinspection UseTomlInstead
dependencies {
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(libs.honoka.android.utils)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

honoka.basic.dependencies {
    kotlinAndroid()
}
