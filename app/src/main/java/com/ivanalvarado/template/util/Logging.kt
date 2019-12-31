package com.ivanalvarado.template.util

import android.util.Log

object Logger {

    fun error(tag: String, message: String, t: Throwable?) {
        if (t != null) {
            Log.e(tag, message, t)
            t.printStackTrace()
        } else {
            Log.e(tag, message)
        }
    }

    fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }
}

fun Any.logError(message: String, tag: String = javaClass.simpleName, t: Throwable? = null) {
    Logger.error(tag, message, t)
}

fun Any.logDebug(message: String, tag: String = javaClass.simpleName) {
    Logger.debug(tag, message)
}