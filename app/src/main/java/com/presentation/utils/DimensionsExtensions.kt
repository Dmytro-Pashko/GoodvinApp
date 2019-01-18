package com.presentation.utils

import com.presentation.utils.ContextProvider.Instance.appContext

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */
private val density
    get() = appContext.resources.displayMetrics.density

private val scaledDensity
    get() = appContext.resources.displayMetrics.scaledDensity

fun Int.dp2px() = this * density
fun Int.dp2pxI() = dp2px().toInt()
fun Float.dp2px() = this * density
fun Float.dp2pxI() = dp2px().toInt()

fun Int.px2dp() = this / density
fun Float.px2dp() = this / density

fun Int.sp2px() = this * scaledDensity
fun Float.sp2px() = this * scaledDensity

fun Int.px2sp() = this / scaledDensity
fun Float.px2sp() = this / scaledDensity
