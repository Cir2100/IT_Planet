package com.example.it_planet

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException


class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var textView: TextView
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "KotlinApp"
        listView = findViewById<ListView>(R.id.userlist)
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.btnParseHTML)
        button.setOnClickListener {
            getHtmlFromWeb()
        }
        listView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val link: String = parent.getItemAtPosition(position).toString().substringAfter(' ').substringBefore(' ')
            // val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            //startActivity(browserIntent)
            val intent = Intent(this@MainActivity, ArticleActivity::class.java)
            startActivity(intent.putExtra("link", link))
        })

    }
    private fun getHtmlFromWeb() {
        Thread(Runnable {
            var index = 0
            val arrayAdapter: ArrayAdapter<String>
            val stringBuilder = StringBuilder()
            val stringBuilderTitle = StringBuilder()
            var strings: MutableList<String> = mutableListOf()
            try {
                val doc: Document = Jsoup.connect("https://vkist.guap.ru/").get()
                val title: String = doc.title()
                val links: Elements = doc.select("article")
                stringBuilderTitle.append(title).append("\n")
                for (link in links) {
                    stringBuilder.append("\n").append("Link: ").append(link.select("a[href]").attr("href")).append(" \n").append("Text : ").append(link.select("h2").text())
                    strings.add(index, stringBuilder.toString())
                    index++
                    stringBuilder.clear()
                }
            } catch (e: IOException) {
                stringBuilder.append("Error : ").append(e.message).append("\n")
            }
            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)
            runOnUiThread {
                textView.text = stringBuilderTitle.toString()
                listView.adapter = arrayAdapter
            }
        }).start()
    }
    private fun onItemClick() {
        //Thread(Runnable {
        //    runOnUiThread {
        textView.setText("dfgd")
        //   }
        // }).start()
    }
}