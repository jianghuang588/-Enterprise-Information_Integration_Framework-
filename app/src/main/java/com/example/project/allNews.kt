package com.example.project
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
private var current: String? = null

class allNews: AppCompatActivity()  {

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_all_news)
        val toolbar2: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar2)

        //tweet activity page data save send to all news page
        current = intent.getStringExtra("result")!!
        title = getString(R.string.tweet_titles, current)


        recyclerView = findViewById(R.id.news)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val newsManager = newsManager()
        //https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
        // GlobalScope.launch(Dispatchers.IO)  this line is reference from above website
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tweet = newsManager.retrieveAll(current.toString())
                val tweets = tweet.toList()
                runOnUiThread {
                    val adapter = TweetAdapter(tweets)
                    recyclerView.adapter = adapter
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@allNews,
                        "fail to retrieve news",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}