package com.jacob.testskyweb.models

import com.google.gson.annotations.SerializedName

data class FilmModel(val author: String = "",
                     val width: Int = 0,
                     @SerializedName("download_url")
                     val downloadUrl: String = "",
                     val id: String = "",
                     val url: String = "",
                     val height: Int = 0)