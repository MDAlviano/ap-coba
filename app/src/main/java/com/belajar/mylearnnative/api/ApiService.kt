package com.belajar.mylearnnative.api

import android.util.Log
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object ApiService {

    const val BASE_URL = " http://10.0.2.2:5000"
    const val TAG = "API_SERVICE"

    fun postRequest(endpoint: String, jsonBody: JSONObject): String? {
        val url = URL("$BASE_URL/$endpoint")
        val connection = url.openConnection() as HttpURLConnection
        return try {
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            val outputStream = connection.outputStream
            outputStream.write(jsonBody.toString().toByteArray())
            outputStream.flush()
            outputStream.close()

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                connection.inputStream.bufferedReader().use { it.readText() }
            } else {
                val errorResponse = connection.errorStream?.bufferedReader()?.use { it.readText() }
                errorResponse ?: "Error: ${connection.responseCode}"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Exception: ${e.message}"
        } finally {
            connection.disconnect()
        }
    }

    fun getRequest(endpoint: String): String? {
        val url = URL("$BASE_URL/$endpoint")
        val connection = url.openConnection() as HttpURLConnection
        return try {
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json")
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                connection.inputStream.bufferedReader().use { it.readText() }
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Exception: ${e.message}"
        } finally {
            connection.disconnect()
        }
    }

}