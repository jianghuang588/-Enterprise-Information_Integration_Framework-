package com.example.project

import java.io.Serializable

//define constructor
data class Tweet (
    val iconUrl: String,
    val username: String,
    val content: String,
    val handle: String

) : Serializable
