package com.belajar.mylearnnative.model

import android.os.Parcel
import android.os.Parcelable

data class Player(
    val id: Int,
    val playerRoleId: Int,
    val teamId: Int,
    val fullName: String,
    val ign: String,
    val image: String,
    val playerRole: PlayerRole,
    val team: Team
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(PlayerRole::class.java.classLoader)!!,
        parcel.readParcelable(Team::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(playerRoleId)
        parcel.writeInt(teamId)
        parcel.writeString(fullName)
        parcel.writeString(ign)
        parcel.writeString(image)
        parcel.writeParcelable(playerRole, flags)
        parcel.writeParcelable(team, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
