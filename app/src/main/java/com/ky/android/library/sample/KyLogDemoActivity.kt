package com.ky.android.library.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ky.android.library.R
import com.ky.android.library.log.KyLog
import com.ky.android.library.log.KyLogManager
import com.ky.android.library.log.KyViewPrinter

class KyLogDemoActivity : AppCompatActivity() {
    var viewPrinter: KyViewPrinter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ky_log_demo)
        viewPrinter = KyViewPrinter(this)
        findViewById<View>(R.id.btn_log).setOnClickListener {
            printLog()
        }
        viewPrinter!!.viewProvider.showFloatingView()
        KyLogManager.getInstance().addPrinter(viewPrinter)
    }

    private fun printLog() {
        KyLog.i("test")
    }
}