package com.example.myapplication.base

import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * Glide加载图片
 */
@BindingAdapter("bind:img")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url).apply(RequestOptions.centerCropTransform())
        .into(view)
}


/**
 * 绑定 SwipeRefreshLayout 颜色，刷新状态，监听事件
 */
@BindingAdapter(
    value = ["bind:refreshColor", "bind:refreshListener"],
    requireAll = false
)
fun bindRefresh(
    refreshLayout: SwipeRefreshLayout,
    color: Int,
    listener: SwipeRefreshLayout.OnRefreshListener
) {
    refreshLayout.setColorSchemeResources(color)
    refreshLayout.setOnRefreshListener(listener)
}


/**
 * 绑定 webview 的 url
 */
@BindingAdapter("bind:url")
fun bindWebUrl(webView: WebView, url: String?) {
    if (url.isNullOrBlank()) return
    webView.loadUrl(url)
}