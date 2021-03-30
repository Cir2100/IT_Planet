package com.example.it_planet

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient


class ArticleActivity : Activity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_article)
        val link: String = getIntent().getStringExtra("link").toString()
        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.getSettings().setJavaScriptEnabled(true)
        webView!!.loadUrl(link)
    }
}