package com.example.dictionary.models.network.apis.yandex

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Translation {

    @SerializedName("code")
    @Expose
    val code: Int? = null

    @SerializedName("lang")
    @Expose
    val lang: String? = null

    @SerializedName("text")
    @Expose
    val text: List<String>? = null
}
