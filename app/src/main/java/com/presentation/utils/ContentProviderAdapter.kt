package com.presentation.utils

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import java.lang.UnsupportedOperationException

open class ContentProviderAdapter : ContentProvider(){

    override fun insert(uri: Uri, values: ContentValues?) = throw UnsupportedOperationException()

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?,
        sortOrder: String?): Cursor? = throw UnsupportedOperationException()

    override fun onCreate() = true

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?)= throw UnsupportedOperationException()

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = throw UnsupportedOperationException()

    override fun getType(uri: Uri) = throw UnsupportedOperationException()

}