package com.example.project
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    //global variable
    private lateinit var searchText: EditText

    private lateinit var searchButton: Button

    private lateinit var viewMapButton: Button

    private lateinit var viewHeadlineButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences: SharedPreferences = getSharedPreferences(
            "android-tweet",
            Context.MODE_PRIVATE
        )

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        searchText = findViewById(R.id.inputBox1)
        searchButton = findViewById(R.id.button1)
        viewMapButton = findViewById(R.id.button2)
        viewHeadlineButton = findViewById(R.id.button3)

        searchButton.isEnabled = false

        searchText.addTextChangedListener(textWatcher)
        searchButton.addTextChangedListener(textWatcher)
        viewMapButton.addTextChangedListener(textWatcher)
        viewHeadlineButton.addTextChangedListener(textWatcher)

        // save the search word
        val saveSearchText = preferences.getString("SearchTerm", "")
        searchText.setText(saveSearchText)

        searchButton.setOnClickListener { view: View ->
            Log.d("MainActivity", "onClick() called")

            val inputtedSearchText: String = searchText.text.toString()
            preferences
                .edit()
                .putString("SearchTerm", inputtedSearchText)
                .apply()

            //intent activity
            val intent: Intent = Intent(this, TweetActivity::class.java)
            intent.putExtra("SearchTerm", inputtedSearchText)
            startActivity(intent)
        }


        viewMapButton.setOnClickListener { view: View ->
            Log.d("MapActivity", "onClick() called")
            val intent: Intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        viewHeadlineButton.setOnClickListener {
            val inputtedSearchText: String = searchText.text.toString()
            Log.d("headline", "onClick() called")
            val intent: Intent = Intent(this,topHeadLine::class.java)
            intent.putExtra("SearchTerm", inputtedSearchText)
            startActivity(intent)
        }
    }

        //reaction to the input
    private val textWatcher = object  : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val inputtedSearchText: String = searchText.text.toString()
            val enable: Boolean = inputtedSearchText.trim().isNotEmpty()
            searchButton.isEnabled = enable
        }
        override fun afterTextChanged(s: Editable) {}
    }
}