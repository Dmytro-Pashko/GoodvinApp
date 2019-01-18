package com.presentation.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.presentation.utils.ContextProvider.Instance.appContext

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */
fun Intent.startService(context: Context = appContext) {
    context.startService(this)
}

fun Intent.sendBroadcast() = appContext.sendBroadcast(this)

fun Intent.startActivity(context: Context? = appContext): Boolean = try {
    context?.startActivity(this)
    true
} catch (e: Exception) {
    e.printStackTrace()
    false
}

inline fun IntentFilter.registerBroadcastReceiver(crossinline block: (Intent) -> Unit) = object : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) = block(intent)
}.also { appContext.registerReceiver(it, this) }

fun BroadcastReceiver?.unregister() = appContext.unregisterReceiver(this)
