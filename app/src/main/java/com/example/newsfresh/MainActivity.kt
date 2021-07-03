package com.example.newsfresh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request

import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)
        fetchData()
         mAdapter = NewsListAdapter(this)
        recyclerView.adapter=mAdapter
    }
    private  fun fetchData() {
        val url="https://newsapi.org/v2/everything?q=keyword&apiKey=de7e587637d74772b311fc6fd7fe11e2"
        val jsonObjectRequest = object:JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val newsJsonArray=it.getJSONArray("articles")
                val newsArray =ArrayList<News>()
                for (i in 0 until newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News (
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),

                        )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            {

            }

        )
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Api-Key"] = "de7e587637d74772b311fc6fd7fe11e2/v2/top-headlines"
                return headers
            }
        }
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
    }
}