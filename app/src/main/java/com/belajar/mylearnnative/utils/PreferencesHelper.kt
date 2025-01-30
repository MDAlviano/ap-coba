package com.belajar.mylearnnative.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUsername(username: String) {
        sharedPreferences.edit().putString("USERNAME", username).apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString("USERNAME", null)
    }

}