package com.belajar.mylearnnative.repository

import com.belajar.mylearnnative.api.ApiService
import org.json.JSONObject

object AuthRepository {

    fun login(username: String, password: String): String? {
        val json = JSONObject().apply {
            put("usernameOrEmail", username)
            put("password", password)
        }
        return ApiService.postRequest("api/sign-in", json)
    }

    fun register(fullName: String, username: String, email: String, phoneNumber: String, password: String): String? {
        val json = JSONObject().apply {
            put("fullName", fullName)
            put("username", username)
            put("email", email)
            put("phoneNumber", phoneNumber)
            put("password", password)
        }
        return ApiService.postRequest("api/sign-up", json) // error
    }


}