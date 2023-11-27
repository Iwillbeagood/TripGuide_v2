import com.jun.tripguide.configureHiltAndroid
import com.jun.tripguide.configureKotestAndroid
import com.jun.tripguide.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotestAndroid()
