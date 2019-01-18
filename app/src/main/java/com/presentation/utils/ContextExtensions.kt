package com.presentation.utils

import android.content.Context
import android.view.LayoutInflater
import com.presentation.utils.ContextProvider.Instance.appContext

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */
val appInflater = appContext.inflater()

fun Context.inflater(): LayoutInflater = LayoutInflater.from(this)

