package com.jun.tripguide_v2.core.data.mapper

import android.text.Html

fun String.htmlToString() = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()