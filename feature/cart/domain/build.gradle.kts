plugins {
    alias(libs.plugins.nowinandroid.android.feature)
}

android {
    namespace = "com.fajarxfce.feature.cart.domain"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(libs.javax.inject)
    implementation("androidx.paging:paging-runtime:3.3.6")
    implementation("androidx.paging:paging-compose:3.3.6")
}