package com.example.dictionary.pojos

import android.arch.persistence.room.*
import android.os.Parcel
import android.os.Parcelable

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
    var isSaved: Boolean): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(valueFrom)
        parcel.writeString(valueTo)
        parcel.writeString(langFrom)
        parcel.writeString(langTo)
        parcel.writeByte(if (isSaved) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun isSame(other: InfoTranslation): Boolean =
        other.valueFrom == valueFrom && other.valueTo == valueTo

    companion object CREATOR : Parcelable.Creator<InfoTranslation> {
        override fun createFromParcel(parcel: Parcel): InfoTranslation {
            return InfoTranslation(parcel)
        }

        override fun newArray(size: Int): Array<InfoTranslation?> {
            return arrayOfNulls(size)
        }
    }
}
