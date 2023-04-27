package com.gramzin.hackathon.data

import android.os.Parcel
import android.os.Parcelable
import com.gramzin.hackathon.java.ResultWord

data class Word(
    val word: String,
    val indexes: IntArray,
    val children: List<Word>,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createIntArray()!!,
        parcel.createTypedArrayList(CREATOR)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(word)
        parcel.writeIntArray(indexes)
        parcel.writeTypedList(children)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Word> {
        override fun createFromParcel(parcel: Parcel): Word {
            return Word(parcel)
        }

        override fun newArray(size: Int): Array<Word?> {
            return arrayOfNulls(size)
        }
    }
}

fun ResultWord.toWord(): Word{
    return Word(word, indexes, children.map { it.toWord() })
}
