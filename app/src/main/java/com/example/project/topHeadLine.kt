package com.example.project
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private lateinit var spinner: Spinner
private lateinit var recyclerView: RecyclerView
private var current: String? = null
private lateinit var page: TextView
private lateinit var nextButton: Button
private lateinit var prevButton: Button

class topHeadLine : AppCompatActivity()   {

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_headline)

        val toolbar: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)

        current = intent.getStringExtra("SearchTerm")!!
        title = getString(R.string.tweet_titles1, current)


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
        spinner.onItemSelectedListener = SpinnerActivity()
        page = findViewById(R.id.page)
        nextButton = findViewById(R.id.nextButton)
        prevButton = findViewById(R.id.prevButton)
        prevButton.isEnabled = false

//.
        nextButton.setOnClickListener {
            val current = page.text
            when (page.text) {
                "0/4" -> {
                    page.text = "1/4"
                    prevButton.isEnabled = true

                }
                "1/4" -> {
                    page.text = "2/4"
                    prevButton.isEnabled = true

                }
                "2/4" -> {
                    page.text = "3/4"
                    prevButton.isEnabled = true

                }
                "3/4" -> {
                    page.text = "4/4"
                    nextButton.isEnabled = false
                }
            }
        }

        prevButton.setOnClickListener {

            when (page.text) {
                "4/4" -> {
                    page.text = "3/4"
                    nextButton.isEnabled = true
                }
                "3/4" -> {
                    page.text = "2/4"
                    nextButton.isEnabled = true
                }
                "2/4" -> {
                    page.text = "1/4"
                    nextButton.isEnabled = true
                }
                "1/4" -> {
                    page.text = "0/4"
                    prevButton.isEnabled = false
                }
            }
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
                    val tweet = newsManager.retrieveTopHeadLineBusiness()
                    val tweets = tweet.toList()
                    runOnUiThread {
                        val adapter = TweetAdapter(listOf(tweets[0], tweets[1]))
                        when (pos) {
                            1 -> recyclerView.adapter = adapter
                        }
                        prevButton.isEnabled = false
                        nextButton.setOnClickListener {
                            val curr = page.text
                            when (curr) {

                                "0/4" -> {
                                    page.text = "1/4"
                                    prevButton.isEnabled = true
                                    val adapter = TweetAdapter(listOf(tweets[2], tweets[3]))
                                    recyclerView.adapter = adapter

                                }

                                "1/4" -> {
                                    page.text = "2/4"
                                    prevButton.isEnabled = true
                                    val adapter = TweetAdapter(listOf(tweets[3], tweets[4]))
                                    recyclerView.adapter = adapter

                                }

                                "2/4" -> {
                                    page.text = "3/4"
                                    prevButton.isEnabled = true
                                    val adapter = TweetAdapter(listOf(tweets[5], tweets[6]))
                                    recyclerView.adapter = adapter
                                }

                                "3/4" -> {
                                    page.text = "4/4"
                                    nextButton.isEnabled = false
                                    val adapter = TweetAdapter(listOf(tweets[7], tweets[8]))
                                    recyclerView.adapter = adapter
                                }
                            }
                        }

                        prevButton.setOnClickListener {

                            when (page.text) {
                                "4/4" -> {
                                    page.text = "3/4"
                                    nextButton.isEnabled = true
                                    val adapter = TweetAdapter(listOf(tweets[5], tweets[6]))
                                    recyclerView.adapter = adapter
                                }

                                "3/4" -> {
                                    page.text = "2/4"
                                    nextButton.isEnabled = true
                                    val adapter = TweetAdapter(listOf(tweets[3], tweets[4]))
                                    recyclerView.adapter = adapter
                                }

                                "2/4" -> {
                                    page.text = "1/4"
                                    nextButton.isEnabled = true
                                    val adapter = TweetAdapter(listOf(tweets[2], tweets[3]))
                                    recyclerView.adapter = adapter
                                }

                                "1/4" -> {
                                    page.text = "0/4"
                                    prevButton.isEnabled = false
                                    val adapter = TweetAdapter(listOf(tweets[0], tweets[1]))
                                    recyclerView.adapter = adapter
                                }
                            }

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
                    val tweet = newsManager.retrieveTopHeadEntertainment()
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
                    val tweet = newsManager.retrieveTopHeadGeneral()
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
                    val tweet = newsManager.retrieveTopHeadHealth()
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
                    val tweet = newsManager.retrieveTopHeadScience()
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
                    val tweet = newsManager.retrieveTopHeadSports()
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
                    val tweet = newsManager.retrieveTopHeadTechnology()
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