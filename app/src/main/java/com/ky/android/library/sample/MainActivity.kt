package com.ky.android.library.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ky.android.library.R
import com.ky.android.library.log.KyLog

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_log -> {
                startActivity(Intent(this, KyLogDemoActivity::class.java))
            }
        }
    }


}