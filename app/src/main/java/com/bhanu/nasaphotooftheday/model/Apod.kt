package com.bhanu.nasaphotooftheday.model

import java.util.*


/**
 * Created by Bhanu Prakash Pasupula on 25,Jun-2020.
 * Email: pasupula1995@gmail.com
 */
data class Apod(
    val date:Date,
    val explanation:String,
    val hdurl:String,
    val media_type:String,
    val title:String,
    val url:String
)