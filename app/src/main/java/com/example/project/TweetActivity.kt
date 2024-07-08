package com.example.project
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


private lateinit var recyclerView: RecyclerView
private lateinit var spinner: Spinner
private lateinit var skipAllButton: Button
private var current: String? = null



class TweetActivity : AppCompatActivity() {

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_tweet)
        val toolbar2: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar2)

        //pass the search term from home page to the next page
        current = intent.getStringExtra("SearchTerm")!!
        title = getString(R.string.tweet_title, current)


        //https://developer.android.com/develop/ui/views/components/spinner
        //I reference the below code from above website
        //create spinner for user to choose
        spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinner.adapter = adapter
        }

        recyclerView = findViewById(R.id.news)
        recyclerView.layoutManager = LinearLayoutManager(this)


        spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = SpinnerActivity()

        skipAllButton = findViewById(R.id.skipAllButton)

        skipAllButton.setOnClickListener {
            Log.d("AllNewsActivity", "onClick() called")
                // tweet activity page data  save
                val intent: Intent = Intent(this, allNews::class.java)
                intent.putExtra("result", current)
                startActivity(intent)
        }



    }

    //https://developer.android.com/develop/ui/views/components/spinner
    //I reference the below code from above website
    //this is the reaction after the user touch the spinner
    class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
            val newsManager = newsManager()

            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope
            // GlobalScope.launch(Dispatchers.IO) this line is come from the above website
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val tweet = newsManager.retrieveBusiness(
                        current.toString()
                    )
                    val tweets = tweet.toList()
                    runOnUiThread {
                        val adapter = TweetAdapter(tweets)
                        when (pos) {
                            1 -> recyclerView.adapter = adapter
                        }
                    }
                } catch (exception: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@SpinnerActivity,
                            "fail to retrieve tweet",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope
            // GlobalScope.launch(Dispatchers.IO) this line is come from the above website
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val tweet = newsManager.retrieveEntertainment(
                        current.toString()
                    )
                    val tweets = tweet.toList()
                    runOnUiThread {
                        val adapter = TweetAdapter(tweets)
                        when (pos) {
                            2 -> recyclerView.adapter = adapter
                        }
                    }
                } catch (exception: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@SpinnerActivity,
                            "fail to retrieve tweet",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope
            // GlobalScope.launch(Dispatchers.IO) this line is come from the above website
            GlobalScope.launch(Dispatchers.IO) {

                try {
                    val tweet = newsManager.retrieveGeneral(
                        current.toString()

                    )
                    val tweets = tweet.toList()
                    runOnUiThread {
                        val adapter = TweetAdapter(tweets)
                        when (pos) {
                            3 -> recyclerView.adapter = adapter
                        }
                    }
                } catch (exception: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@SpinnerActivity,
                            "fail to retrieve tweet",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope
            // GlobalScope.launch(Dispatchers.IO) this line is come from the above website
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val tweet = newsManager.retrieveHealth(
                        current.toString()
                    )
                    val tweets = tweet.toList()
                    runOnUiThread {
                        val adapter = TweetAdapter(tweets)
                        when (pos) {
                            4 -> recyclerView.adapter = adapter
                        }
                    }
                } catch (exception: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@SpinnerActivity,
                            "fail to retrieve tweet",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope
            // GlobalScope.launch(Dispatchers.IO) this line is come from the above website
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val tweet = newsManager.retrieveScience(
                        current.toString()
                    )
                    val tweets = tweet.toList()
                    runOnUiThread {
                        val adapter = TweetAdapter(tweets)
                        when (pos) {
                            5 -> recyclerView.adapter = adapter
                        }
                    }
                } catch (exception: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@SpinnerActivity,
                            "fail to retrieve tweet",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope
            // GlobalScope.launch(Dispatchers.IO) this line is come from the above website
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val tweet = newsManager.retrieveSports(
                        current.toString()
                    )
                    val tweets = tweet.toList()
                    runOnUiThread {
                        val adapter = TweetAdapter(tweets)
                        when (pos) {
                            6 -> recyclerView.adapter = adapter
                        }
                    }
                } catch (exception: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@SpinnerActivity,
                            "fail to retrieve tweet",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope
            // GlobalScope.launch(Dispatchers.IO) this line is come from the above website
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val tweet = newsManager.retrieveTechnology(
                        current.toString()
                    )
                    val tweets = tweet.toList()
                    runOnUiThread {
                        val adapter = TweetAdapter(tweets)
                        when (pos) {
                            7 -> recyclerView.adapter = adapter
                        }
                    }
                } catch (exception: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@SpinnerActivity,
                            "fail to retrieve tweet",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        override fun onNothingSelected(parent: AdapterView<*>) {
        }
    }

}