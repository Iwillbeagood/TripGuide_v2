import com.jun.tripguide.configureCoroutineAndroid
import com.jun.tripguide.configureHiltAndroid
import com.jun.tripguide.configureKotest
import com.jun.tripguide.configureKotlinAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()
