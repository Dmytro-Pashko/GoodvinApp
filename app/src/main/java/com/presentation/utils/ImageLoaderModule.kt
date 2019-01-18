package com.presentation.utils

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */
@GlideModule
class ImageLoaderModule : AppGlideModule() {

    private val cacheSize = 1024L * 1024L * 24

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setMemoryCache(LruResourceCache(cacheSize))
        super.applyOptions(context, builder)
    }

    override fun isManifestParsingEnabled() = false
}
