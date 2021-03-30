package com.example.it_planet

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException


class ArticleActivity : Activity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_article)
        val link: String = getIntent().getStringExtra("link").toString()
        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        parseVKIT(link)
        //webView.loadUrl(link)
    }

    private fun parseVKIT(url :String)
    {
        Thread(Runnable {
            val doc: Document = Jsoup.connect(url).get()
            val articles: Elements = doc.select("article")
            val stringBuilder = StringBuilder()
            val html: String = articles[0].html()
            stringBuilder.append("<style type=\"text/css\">\n" +
                    "   a { \n" +
                    "   pointer-events: none;\n" +
                    //"   color: #999;\n" +
                    "   text-decoration: none;\n" +
                    "   }\n" +
                    "   img { \n" +
                    "   height: auto;\n" +
                    "   border: 0;\n" +
                    "   max-width: 100%;\n" +
                    "   }\n" +
                    "blocks-gallery-item__caption {\n" +
                    "position: absolute;\n" +
                    "bottom: 0;\n" +
                    "width: 100%;\n" +
                    "max-height: 100%;\n" +
                    "overflow: auto;\n" +
                    "padding: 3em .77em .7em;\n" +
                    "text-align: center;\n" +
                    "font-size: .8em;\n" +
                    "background: linear-gradient(0deg,rgba(0,0,0,.7),rgba(0,0,0,.3) 70%,transparent);\n" +
                    "box-sizing: border-box;\n" +
                    "margin: 0;\n" +
                        "}\n" +
                    ".blocks-gallery-grid, .wp-block-gallery {\n" +
                    "display: flex;\n" +
                   " flex-wrap: wrap;\n" +
                    "list-style-type: none;\n" +
                    "padding: 0;\n" +
                   " margin: 0;\n" +
                    "}\n" +
                    "iframe {\n" +
                    "max-width: 100%;\n" +
                    "}\n" +
                    "  </style>")
            stringBuilder.append("<head>").append(articles[0].select("head").text()).append("</head>").append("<h1>").append(articles[0].select("h1").text()).append("</h1>").append(articles[0].select("div"))
            runOnUiThread {
                webView.getSettings().setJavaScriptEnabled(true)
                webView.loadData(stringBuilder.toString(), "text/html", null)
            }
        }).start()
    }


}