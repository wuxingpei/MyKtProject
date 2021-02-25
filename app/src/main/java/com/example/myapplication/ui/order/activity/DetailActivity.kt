package com.example.myapplication.ui.order.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.*
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_detail
    }

    override fun initActivity(savedInstanceState: Bundle?) {
        viewTitle.setLeftClickListener(View.OnClickListener { finish() })
            .setTitle(intent.getStringExtra("title"))
        mBinding?.link=intent.getStringExtra("link")
        mBinding?.webView?.let {
            it.settings.apply {
                javaScriptEnabled = true
                javaScriptCanOpenWindowsAutomatically = true
                allowFileAccess = true
                layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
                useWideViewPort = true
                loadWithOverviewMode = true
                setSupportMultipleWindows(true)
                setGeolocationEnabled(true)
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                setAppCacheEnabled(true)
                domStorageEnabled = true
                cacheMode = WebSettings.LOAD_NO_CACHE
            }
            it.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url.isNullOrBlank()) return false

                    if (!url.matches(Regex("(http|https)?://(\\S)+"))) {
                        try {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        } catch (e: Exception) {

                        }
                        return true
                    }

                    view?.loadUrl(url)
                    return true
                }

                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    val url = request?.url.toString()

                    if (url.isBlank()) return false

                    if (!url.matches(Regex("(http|https)?://(\\S)+"))) {
                        try {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        } catch (e: Exception) {

                        }
                        return true
                    }

                    view?.loadUrl(url)
                    return true
                }
            }

            it.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(web: WebView?, newProgress: Int) {
                    super.onProgressChanged(web, newProgress)
                    if (newProgress > 85) {
                    }
                }
            }
        }
    }
}