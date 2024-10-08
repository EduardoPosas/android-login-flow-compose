// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        // other repositories...
        mavenCentral()
    }
    dependencies {
        classpath (libs.hilt.android.gradle.plugin)
        classpath(libs.protobuf.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id ("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.google.protobuf") version "0.9.4" apply false
}