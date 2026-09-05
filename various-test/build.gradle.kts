plugins {
    java
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.lombok)
    alias(libs.plugins.honoka.basic)
}

group = "de.honoka.test"
version = libs.versions.p.root.get()

honoka.basic {
    configs {
        java(17)
        kotlin()
        kapt()
    }

    dependencies {
        lombok()
        kotlin()
    }
}

dependencies {
    implementation(libs.honoka.kotlin.utils)
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    implementation("org.junit.jupiter:junit-jupiter:5.12.2")
}

libs.versions.d.kotlin.coroutines
libs.versions.d.lombok
