plugins {
    alias(libs.plugins.nowinandroid.android.library)
    alias(libs.plugins.nowinandroid.android.library.jacoco)
    alias(libs.plugins.nowinandroid.hilt)
    alias(libs.plugins.nowinandroid.android.feature)
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

android {
    namespace = "com.fajarxfce.core.domain"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}


dependencies {
    api(projects.core.common)
    implementation(libs.kotlinx.coroutines.core)
}