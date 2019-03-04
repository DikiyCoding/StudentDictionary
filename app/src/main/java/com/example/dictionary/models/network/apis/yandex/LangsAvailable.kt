package com.example.dictionary.models.network.apis.yandex

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LangsAvailable {

    @SerializedName("dirs")
    @Expose
    var dirs: List<String>? = null
    @SerializedName("langs")
    @Expose
    var langs: Langs? = null
}
