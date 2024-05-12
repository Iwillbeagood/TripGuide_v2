package com.jun.tripguide_v2.feature.mytravelPlan.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.kakao.sdk.navi.Constants
import com.kakao.sdk.navi.NaviClient
import com.kakao.sdk.navi.model.CoordType
import com.kakao.sdk.navi.model.Location
import com.kakao.sdk.navi.model.NaviOption

fun startKakaoNavigation(context: Context, name: String, x: Double, y: Double) = with(context) {
    if (NaviClient.instance.isKakaoNaviInstalled(context)) {
        navigateToLocation(name, x.toString(), y.toString())
    } else {
        installKakaoNavi()
    }
}

private fun Context.navigateToLocation(name: String, x: String, y: String) {
    startActivity(
        NaviClient.instance.navigateIntent(
            Location(name, x, y),
            NaviOption(coordType = CoordType.WGS84)
        )
    )
}

private fun Context.installKakaoNavi() {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(Constants.WEB_NAVI_INSTALL)
        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    )
}