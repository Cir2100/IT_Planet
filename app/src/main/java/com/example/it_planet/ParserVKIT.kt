package com.example.it_planet

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class ParserVKIT {
    fun getHtmlFromWeb(url: String): MutableList<String> {
        var index = 0
        val stringBuilder = StringBuilder()
        var strings: MutableList<String> = mutableListOf()

        val doc: Document = Jsoup.connect(url).get()
        val links: Elements = doc.select("article")
        for (link in links) {
            stringBuilder.append("\n").append("Link: ").append(link.select("a[href]").attr("href")).append(" \n").append("Text : ").append(link.select("h2").text())
            strings.add(index, stringBuilder.toString())
            index++
            stringBuilder.clear()
        }
        return strings
    }

    fun parse(url: String): String {
        val doc: Document = Jsoup.connect(url).get()
        val articles: Elements = doc.select("article")
        val stringBuilder = StringBuilder()
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
        return stringBuilder.toString()
    }
}
