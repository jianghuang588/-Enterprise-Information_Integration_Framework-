package com.example.project
import android.location.Address
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class newActivity: AppCompatActivity()  {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_news)
        val toolbar2: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar2)


        val currentAddress: Address = intent.getParcelableExtra("address")!!
        title = getString(R.string.tweet_title, currentAddress.adminArea)
        val newsManager = newsManager()

        recyclerView = findViewById(R.id.news)
        recyclerView.layoutManager = LinearLayoutManager(this)


        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope
        // GlobalScope.launch(Dispatchers.IO) this line is come from the above website
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tweets = newsManager.retrieveTweets(
                    currentAddress.adminArea
               )
                val tweet = tweets.toList()
                runOnUiThread {
                    val adapter = TweetAdapter(tweet)
                    recyclerView.adapter = adapter
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@newActivity,
                        "fail to retrieve tweet",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}