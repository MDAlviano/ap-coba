package com.belajar.mylearnnative.model

import android.os.Parcel
import android.os.Parcelable

data class Team(
    val id: Int,
    val name: String,
    val about: String,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val gold: Int,
    val damage: Int,
    val lordKills: Int,
    val tortoiseKills: Int,
    val towerDestroy: Int,
    val logo500: String,
    val logo256: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(about)
        parcel.writeInt(kills)
        parcel.writeInt(deaths)
        parcel.writeInt(assists)
        parcel.writeInt(gold)
        parcel.writeInt(damage)
        parcel.writeInt(lordKills)
        parcel.writeInt(tortoiseKills)
        parcel.writeInt(towerDestroy)
        parcel.writeString(logo500)
        parcel.writeString(logo256)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Team> {
        override fun createFromParcel(parcel: Parcel): Team {
            return Team(parcel)
        }

        override fun newArray(size: Int): Array<Team?> {
            return arrayOfNulls(size)
        }
    }
}
