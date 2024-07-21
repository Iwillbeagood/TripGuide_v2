package com.jun.tripguide

import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "com.jun.tripguide_v2.$name"
    }
}