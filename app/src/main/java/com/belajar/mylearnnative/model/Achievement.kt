package com.belajar.mylearnnative.model

import android.os.Parcel
import android.os.Parcelable

data class Achievement(
    val id: Int,
    val teamId: Int,
    val name: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(teamId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Achievement> {
        override fun createFromParcel(parcel: Parcel): Achievement {
            return Achievement(parcel)
        }

        override fun newArray(size: Int): Array<Achievement?> {
            return arrayOfNulls(size)
        }
    }
}
