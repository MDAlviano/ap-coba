package com.belajar.mylearnnative.model

import android.os.Parcel
import android.os.Parcelable

data class PlayerRole(
    val id: Int,
    val name: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerRole> {
        override fun createFromParcel(parcel: Parcel): PlayerRole {
            return PlayerRole(parcel)
        }

        override fun newArray(size: Int): Array<PlayerRole?> {
            return arrayOfNulls(size)
        }
    }
}
