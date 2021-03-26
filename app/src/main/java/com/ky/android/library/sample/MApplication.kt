package com.ky.android.library.sample

import android.app.Application
import com.google.gson.Gson
import com.ky.android.library.log.*

class MApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKyLog()
    }

    private fun initKyLog() {
        val absolutePath = this.cacheDir.absolutePath
        KyLogManager.init(object : KyLogConfig() {

            override fun injectJsonParser(): JsonParser {
                return JsonParser { src -> Gson().toJson(src) }
            }

            override fun includeThread(): Boolean {
                return true
            }

            override fun getGlobalTag(): String {
                return "KyLog"
            }

            override fun enable(): Boolean {
                return true
            }
        }, KyConsolePrinter(), KyFilePrinter.getInstance(absolutePath, 0))
        KyLog.i(absolutePath)
    }
}