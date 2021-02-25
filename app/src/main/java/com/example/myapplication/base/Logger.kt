package com.example.myapplication.base

import android.util.Log
import com.example.myapplication.BuildConfig
import org.json.JSONArray
import org.json.JSONObject
private var fileName: String = ""
private var lineNumber: Int = -1

interface MLogger {
    val loggerTag: String get() = getTag(javaClass)
}

private fun isDebug() = BuildConfig.DEBUG

fun mLogger(clazz: Class<*>): MLogger = object : MLogger {
    override val loggerTag: String get() = getTag(clazz)
}

inline fun <reified T : Any> mLogger(): MLogger = mLogger(T::class.java)

private fun getTag(clazz: Class<*>) = clazz.simpleName.let {
    if (it.length <= 23) it else it.substring(0, 23)
}

private fun getMethodInfo(thr: Throwable) {
    fileName = thr.stackTrace[1]?.fileName ?: ""
    lineNumber = thr.stackTrace[1]?.lineNumber ?: 0
}

private fun createLog(message: Any): String {
    return "($fileName:$lineNumber): $message"
}

private inline fun mLog(
    logger: MLogger, message: Any?,
    throwable: Throwable?, level: Int,
    func: (String, String) -> Unit,
    funcThrow: (String, String, Throwable) -> Unit
) {
    val tag = logger.loggerTag
    if (Log.isLoggable(tag, level) && isDebug()) {
        if (throwable != null)
            funcThrow(tag, createLog(message?.toString() ?: "null"), throwable)
        else
            func(tag, createLog(message?.toString() ?: "null"))
    }
}

fun MLogger.vPrint(message: Any?, thr: Throwable? = null) {
    getMethodInfo(Throwable())
    mLog(this, message, thr, Log.VERBOSE,
        { tag, msg -> Log.v(tag, msg) },
        { tag, msg, err -> Log.v(tag, msg, err) })
}

fun MLogger.dPrint(message: Any?, thr: Throwable? = null) {
    getMethodInfo(Throwable())
    mLog(this, message, thr, Log.DEBUG,
        { tag, msg -> Log.d(tag, msg) },
        { tag, msg, err -> Log.d(tag, msg, err) })
}

fun MLogger.iPrint(message: Any?, thr: Throwable? = null) {
    getMethodInfo(Throwable())
    mLog(this, message, thr, Log.INFO,
        { tag, msg -> Log.i(tag, msg) },
        { tag, msg, err -> Log.i(tag, msg, err) })
}

fun MLogger.wPrint(message: Any?, thr: Throwable? = null) {
    getMethodInfo(Throwable())
    mLog(this, message, thr, Log.WARN,
        { tag, msg -> Log.w(tag, msg) },
        { tag, msg, err -> Log.w(tag, msg, err) })
}

fun MLogger.ePrint(message: Any?, thr: Throwable? = null) {
    getMethodInfo(Throwable())
    mLog(this, message, thr, Log.ERROR,
        { tag, msg -> Log.e(tag, msg) },
        { tag, msg, err -> Log.e(tag, msg, err) })
}

fun MLogger.vPrint(message: () -> Any?) {
    getMethodInfo(Throwable())
    if (Log.isLoggable(loggerTag, Log.VERBOSE) && isDebug())
        Log.v(loggerTag, createLog(message()?.toString() ?: "null"))
}

fun MLogger.dPrint(message: () -> Any?) {
    getMethodInfo(Throwable())
    if (Log.isLoggable(loggerTag, Log.DEBUG) && isDebug())
        Log.d(loggerTag, createLog(message()?.toString() ?: "null"))
}

fun MLogger.iPrint(message: () -> Any?) {
    getMethodInfo(Throwable())
    if (Log.isLoggable(loggerTag, Log.INFO) && isDebug())
        Log.i(loggerTag, createLog(message()?.toString() ?: "null"))
}

fun MLogger.wPrint(message: () -> Any?) {
    getMethodInfo(Throwable())
    if (Log.isLoggable(loggerTag, Log.WARN) && isDebug())
        Log.w(loggerTag, createLog(message()?.toString() ?: "null"))
}

fun MLogger.ePrint(message: () -> Any?) {
    getMethodInfo(Throwable())
    if (Log.isLoggable(loggerTag, Log.ERROR) && isDebug())
        Log.e(loggerTag, createLog(message()?.toString() ?: "null"))
}

fun MLogger.jsonPrint(errorLevel: Boolean = true, message: () -> String) {
    val json = message()
    getMethodInfo(Throwable())
    val isLoggable = Log.isLoggable(loggerTag, if (errorLevel) Log.ERROR else Log.INFO) && isDebug()

    if (isLoggable) {
        if (json.isBlank()) {
            if (errorLevel) {
                Log.e(loggerTag, "Blank json information")
            } else {
                Log.i(loggerTag, "Blank json information")
            }
            return
        }

        val jsonInformation = try {
            when {
                json.startsWith("{") && json.endsWith("}") -> JSONObject(json).toString(4)
                json.startsWith("[") && json.endsWith("]") -> JSONArray(json).toString(4)
                else -> "bad json information: ($json)"
            }
        } catch (e: Exception) {
            "${e.cause?.message}${System.getProperty("line.separator")}: $json"
        }

        if (errorLevel) {
            Log.e(loggerTag, jsonInformation)
        } else {
            Log.i(loggerTag, jsonInformation)
        }
    }
}