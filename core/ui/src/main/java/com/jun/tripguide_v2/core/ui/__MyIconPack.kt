package com.jun.tripguide_v2.core.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.jun.tripguide_v2.core.ui.myiconpack.Map
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Map)
    return __AllIcons!!
  }
