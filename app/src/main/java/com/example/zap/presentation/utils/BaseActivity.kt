package com.example.zap.presentation.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.zap.R
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun showLoading() {
        pb_loading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        pb_loading.visibility = View.INVISIBLE
    }
}