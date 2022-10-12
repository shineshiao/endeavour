package com.shineshiao.endeavour.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.shineshiao.endeavour.BuildConfig
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.Locale

object LogUtil {
    private val isDebuggable = BuildConfig.DEBUG
    fun println(message: String) {
        if (isDebuggable) {
            println(message)
        }
    }

    // log verbal
    fun v(tag: String, msg: String?) {
        if (msg != null && isDebuggable) {
            Log.v(tag.uppercase(Locale.getDefault()), msg)
        }
    }

    // log debug
    fun d(tag: String, msg: String?) {
        if (msg != null && isDebuggable) {
            Log.d(tag.uppercase(Locale.getDefault()), msg)
        }
    }

    // log info
    fun i(tag: String, msg: String?) {
        if (msg != null && isDebuggable) {
            Log.i(tag.uppercase(Locale.getDefault()), msg)
        }
    }

    // log errorData
    fun e(tag: String, msg: String?) {
        if (msg != null && isDebuggable) {
            Log.e(tag.uppercase(Locale.getDefault()), msg)
        }
    }

    fun printStackTrace(e: Exception?) {
        if (e != null && isDebuggable) {
            e.printStackTrace()
        }
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun formatLog(name: String): String {
        return "[$name] ->"
    }

    fun createFileLog(context: Context, logData: String): File? {
        val path = context.cacheDir
        if (path.canWrite()) {
            val logFile = File(path, "Log.txt")
            val logWriter = FileWriter(logFile)
            val out = BufferedWriter(logWriter)
            out.write(logData)
            out.close()
            return logFile
        }
        return null
    }
}
