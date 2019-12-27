package com.example.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.example_webview_activity.*

class ExampleWebViewActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_webview_activity)

        initView()
    }

    private fun initView() {
        viewPager.adapter = ExampleWebViewAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> startActivity(Intent(this, ExampleWebViewActivity::class.java))
        }
    }
}