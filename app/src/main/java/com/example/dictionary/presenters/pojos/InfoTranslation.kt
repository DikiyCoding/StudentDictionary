package com.example.dictionary.presenters.pojos

import android.arch.persistence.room.*

@Entity(tableName = "translations", primaryKeys = ["value_from", "value_to"])
class InfoTranslation(
    @ColumnInfo(name = "value_from")
    var valueFrom: String,
    @ColumnInfo(name = "value_to")
    var valueTo: String,
    @ColumnInfo(name = "language_from")
    var langFrom: String,
    @ColumnInfo(name = "language_to")
    var langTo: String,
    @ColumnInfo(name = "is_saved")
    var isSaved: Boolean) {
    fun isSame(other: InfoTranslation): Boolean {
        return other.valueFrom == valueFrom && other.valueTo == valueTo
    }
}
