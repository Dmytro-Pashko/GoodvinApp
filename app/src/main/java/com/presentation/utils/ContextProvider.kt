package com.presentation.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
 class ContextProvider : ContentProviderAdapter(){

    companion object Instance{
        lateinit var appContext: Context
    }

    override fun onCreate(): Boolean {
        if (context != null) {
            appContext = context!!
        }
        return true
    }
}