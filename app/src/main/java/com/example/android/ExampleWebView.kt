package com.example.android

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.webkit.*
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class ExampleWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr), LifecycleObserver {

    init {
        settings.apply {
            textZoom = 100
            builtInZoomControls = true

            javaScriptEnabled = true
            domStorageEnabled = true

            useWideViewPort = true
            loadWithOverviewMode = true

            mediaPlaybackRequiresUserGesture = false

            setSupportMultipleWindows(true)
            javaScriptCanOpenWindowsAutomatically = true

            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        CookieManager.getInstance().run {
            setAcceptCookie(true)
            setAcceptThirdPartyCookies(this@ExampleWebView, true)
        }

        setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
        setDownloadListener { url, _, _, _, _ ->
            startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse(url)), null)
        }

        webViewClient = WebViewClient()
        webChromeClient = WebChromeClient()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
        super.onResume()

        evaluateJavascript(context.getString(R.string.webview_safe_exec_script, "onResume();"), null)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
        CookieManager.getInstance().flush()

        evaluateJavascript(context.getString(R.string.webview_safe_exec_script, "onPause();"), null)

        super.onPause()
    }
}