package com.example.dictionary.models.network.apis.yandex

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LangsAvailable {

    @SerializedName("dirs")
    @Expose
    lateinit var dirs: List<String>
    @SerializedName("langs")
    @Expose
    lateinit var langs: Langs
}
