package com.example.project
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit


class newsManager {

    private val okHttpClient: OkHttpClient

    init {
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
        okHttpClient = builder.build()

        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(15, TimeUnit.SECONDS)
        builder.writeTimeout(15, TimeUnit.SECONDS)
    }



    fun retrieveBusiness(header: String): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=$header&qInTitle=business&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "business")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                //val name = source.getString("name")
                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")


                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveEntertainment(header: String): MutableCollection<Tweet> {
        //
        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=$header&qInTitle=entertainment&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "entertainment")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")

                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }
    fun retrieveGeneral(header: String): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=$header&qInTitle=general&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "general")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")

                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveHealth(header: String): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=$header&qInTitle=health&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "health")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")

                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveScience(header: String): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=$header&qInTitle=science&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "science")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")

                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveSports(header: String): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=$header&qInTitle=sport&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "sport")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")

                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveTechnology(header: String): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=$header&qInTitle=technology&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "technology")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")

                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveAll(header: String): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?q=$header&qInTitle=business&entertainment&general&health&science&sports&technology&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "business")
            .header("q", "entertainment")
            .header("q", "general")
            .header("q", "health")
            .header("q", "science")
            .header("q", "sports")
            .header("q", "technology")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")

                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }


    fun retrieveTweets(address: String): MutableCollection<Tweet> {
        Log.d(address,"d")

        val request = Request.Builder()

            .url("https://newsapi.org/v2/everything?q=$address&apiKey=6ed093e84341406f85bdca8675389406")
            .header("geocode","address")
            .header("q", "business")
            .header("q", "entertainment")
            .header("q", "general")
            .header("q", "health")
            .header("q", "science")
            .header("q", "sports")
            .header("q", "technology")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            //val statuses: JSONArray = json.getJSONArray("tv_shows")
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")


                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )
                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveTopHeadLineBusiness(): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?q=business&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "business")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")


                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }


    fun retrieveTopHeadEntertainment(): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?q=entertainment&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "business")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")


                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveTopHeadGeneral(): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?q=general&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "business")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")


                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveTopHeadHealth(): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?q=health&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "business")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")


                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveTopHeadScience(): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?q=science&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "business")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")


                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveTopHeadSports(): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?q=sports&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "business")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")


                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

    fun retrieveTopHeadTechnology(): MutableCollection<Tweet> {
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?q=technology&apiKey=6ed093e84341406f85bdca8675389406")
            .header("q", "business")
            .build()
        val response = okHttpClient.newCall(request).execute()
        val tweets: MutableCollection<Tweet> = mutableListOf()
        val responseString: String? = response.body?.string()

        if (!responseString.isNullOrEmpty() && response.isSuccessful) {
            val json: JSONObject = JSONObject(responseString)
            val statuses: JSONArray = json.getJSONArray("articles")

            for (i in 0 until statuses.length()) {
                val curr = statuses.getJSONObject(i)
                val source = curr.getJSONObject("source")

                val name = curr.getString("title")

                val description = curr.getString("content")

                val url = curr.getString("url")

                val urlToImage = curr.getString("urlToImage")


                val tweet = Tweet(
                    handle = url,
                    username = name,
                    content = description,
                    iconUrl = urlToImage
                )

                tweets.add(tweet)
            }
        }
        return tweets
    }

}