package com.presentation.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.presentation.utils.ContextProvider.Instance.appContext

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */
fun Int.asResourceLayout() = asResourceLayout(appContext)
fun Int.asResourceLayout(context: Context) = asResourceLayout(context, null)
fun Int.asResourceLayout(context: Context, parent: ViewGroup?) =
    LayoutInflater.from(context).inflate(this, parent, false)!!

fun Int.asResourceColor() = asResourceColor(appContext)
fun Int.asResourceColor(context: Context) = ContextCompat.getColor(context, this)

fun Int.asResourceColorList() = asResourceColorList(appContext)
fun Int.asResourceColorList(context: Context) = ContextCompat.getColorStateList(context, this)

fun Int.asResourceInt() = asResourceInt(appContext)
fun Int.asResourceInt(context: Context) = context.resources.getInteger(this)

fun Int.asResourceBool() = asResourceBool(appContext)
fun Int.asResourceBool(context: Context) = context.resources.getBoolean(this)

fun Int.asResourceDrawable() = asResourceDrawable(appContext)
fun Int.asResourceDrawable(context: Context) = AppCompatResources.getDrawable(context, this)!!

fun Int.asResourceDimension() = asResourceDimension(appContext)
fun Int.asResourceDimension(context: Context) = context.resources.getDimension(this)

fun Int.asResourceText() = asResourceText(appContext)
fun Int.asResourceText(context: Context): CharSequence = context.resources.getText(this)

fun Int.asResourceString() = asResourceString(appContext)
fun Int.asResourceString(context: Context): String = context.resources.getString(this)

fun Int.asResourceString(vararg arguments: Any) = asResourceString(appContext, *arguments)
fun Int.asResourceString(context: Context, vararg arguments: Any): String =
    context.resources.getString(this, *arguments)

fun Int.asResourcePlural(count: Int, vararg arguments: Any) = asResourcePlural(appContext, count, *arguments)
fun Int.asResourcePlural(context: Context, count: Int, vararg arguments: Any): String =
    context.resources.getQuantityString(this, count, *arguments)

fun Int.asResourceTypeArray() = asResourceTypeArray(appContext)
fun Int.asResourceTypeArray(context: Context) = context.resources.obtainTypedArray(this)

fun Int.asResourceIntArray() = asResourceIntArray(appContext)
fun Int.asResourceIntArray(context: Context) = context.resources.getIntArray(this)

fun Int.asResourceStringArray() = asResourceStringArray(appContext)
fun Int.asResourceStringArray(context: Context): Array<String> = context.resources.getStringArray(this)

fun Int.asResourceIdsArray() = asResourceIdsArray(appContext)
fun Int.asResourceIdsArray(context: Context) = this.asResourceTypeArray(context).let { ids ->
    IntArray(ids.length()) { ids.getResourceId(it, 0) }.also { ids.recycle() }
}