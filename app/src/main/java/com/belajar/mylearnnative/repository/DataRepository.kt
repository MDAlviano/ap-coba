package com.belajar.mylearnnative.repository

import android.util.Log
import com.belajar.mylearnnative.api.ApiService
import com.belajar.mylearnnative.model.Achievement
import com.belajar.mylearnnative.model.Player
import com.belajar.mylearnnative.model.PlayerRole
import com.belajar.mylearnnative.model.Team
import org.json.JSONArray
import org.json.JSONException

object DataRepository {

    const val TAG = "Data Repository"

    fun getListTeams(endpoint: String): List<Team> {
        val teams = mutableListOf<Team>()
        try {
            val response = ApiService.getRequest("api/teams") ?: return emptyList()
            Log.d(TAG, "Raw response: $response")

            try {
                val jsonArray = JSONArray(response)
                parseTeamsFromJson(jsonArray, teams)
            } catch (e: JSONException) {
                Log.e(TAG, "JSON parsing error: ${e.message}")
                Log.e(TAG, "Invalid JSON response: $response")
            }
        } catch (e: JSONException) {
            Log.e(TAG, "Error getting data: ${e.message}")
        }
        return teams
    }

    fun getListPlayers(endpoint: String): List<Player> {
        val players = mutableListOf<Player>()
        try {
            val response = ApiService.getRequest("api/players") ?: return emptyList()
            Log.d(TAG, "Raw response: $response")
            try {
                val jsonArray = JSONArray(response)
                parsePlayersFromJson(jsonArray, players)
            } catch (e: JSONException) {
                Log.e(TAG, "JSON parsing error: ${e.message}")
                Log.e(TAG, "Invalid JSON response: $response")
            }
        } catch (e: JSONException) {
            Log.e(TAG, "Error getting data: ${e.message}")
        }
        return players
    }

    fun getListAchievement(endpoint: String, teamId: Int): List<Achievement> {
        val achievements = mutableListOf<Achievement>()
        try {
            val response = ApiService.getRequest("api/achievements/$teamId") ?: return emptyList()
            Log.d(TAG, "Raw response: $response")
            try {
                val jsonArray = JSONArray(response)
                parseAchievementsFromJson(jsonArray, achievements)
            } catch (e: JSONException) {
                Log.e(TAG, "JSON parsing error: ${e.message}")
                Log.e(TAG, "Invalid JSON response: $response")
            }
        } catch (e: JSONException) {
            Log.e(TAG, "Error getting data: ${e.message}")
        }
        return achievements
    }

    fun getListPlayerTeam(endpoint: String, teamId: Int): List<Player> {
        val players = mutableListOf<Player>()
        try {
            val response = ApiService.getRequest("api/players/team/$teamId") ?: return emptyList()
            Log.d(TAG, "Raw response: $response")
            try {
                val jsonArray = JSONArray(response)
                parsePlayersFromJson(jsonArray, players)
            } catch (e: JSONException) {
                Log.e(TAG, "JSON parsing error: ${e.message}")
                Log.e(TAG, "Invalid JSON response: $response")
            }
        } catch (e: JSONException) {
            Log.e(TAG, "Error getting data: ${e.message}")
        }
        return players
    }

    private fun parseAchievementsFromJson(
        jsonArray: JSONArray,
        achievement: MutableList<Achievement>
    ) {
        for (i in 0 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i)
                achievement.add(
                    Achievement(
                        id = jsonObject.getInt("id"),
                        teamId = jsonObject.getInt("teamId"),
                        name = jsonObject.getString("name")
                    )
                )
            } catch (e: JSONException) {
                Log.e(TAG, "Error parsing team at index $i: ${e.message}", e)
            }
        }
    }

    private fun parseTeamsFromJson(jsonArray: JSONArray, teams: MutableList<Team>) {
        for (i in 0 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i)
                teams.add(
                    Team(
                        id = jsonObject.getInt("id"),
                        name = jsonObject.getString("name"),
                        about = jsonObject.getString("about"),
                        kills = jsonObject.getInt("kills"),
                        deaths = jsonObject.getInt("deaths"),
                        assists = jsonObject.getInt("assists"),
                        gold = jsonObject.getInt("gold"),
                        damage = jsonObject.getInt("damage"),
                        lordKills = jsonObject.getInt("lordKills"),
                        tortoiseKills = jsonObject.getInt("tortoiseKills"),
                        towerDestroy = jsonObject.getInt("towerDestroy"),
                        logo256 = jsonObject.getString("logo256"),
                        logo500 = jsonObject.getString("logo500")
                    )
                )
            } catch (e: JSONException) {
                Log.e(TAG, "Error parsing team at index $i: ${e.message}", e)
            }
        }
    }

    private fun parsePlayersFromJson(jsonArray: JSONArray, players: MutableList<Player>) {
        for (i in 0 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i)

                val roleJsonObject = jsonObject.optJSONObject("playerRole")
                val role = PlayerRole(
                    id = roleJsonObject?.getInt("id") ?: 0,
                    name = roleJsonObject?.getString("name") ?: "Unknown"
                )

                val teamJsonObject = jsonObject.optJSONObject("team")
                val team = Team(
                    id = teamJsonObject?.getInt("id") ?: 0,
                    name = teamJsonObject?.getString("name") ?: "unknown",
                    about = teamJsonObject?.getString("about") ?: "unknown",
                    kills = teamJsonObject?.getInt("kills") ?: 0,
                    deaths = teamJsonObject?.getInt("deaths") ?: 0,
                    assists = teamJsonObject?.getInt("assists") ?: 0,
                    gold = teamJsonObject?.getInt("gold") ?: 0,
                    damage = teamJsonObject?.getInt("damage") ?: 0,
                    lordKills = teamJsonObject?.getInt("lordKills") ?: 0,
                    tortoiseKills = teamJsonObject?.getInt("tortoiseKills") ?: 0,
                    towerDestroy = teamJsonObject?.getInt("towerDestroy") ?: 0,
                    logo256 = teamJsonObject?.getString("logo256") ?: "unknown",
                    logo500 = teamJsonObject?.getString("logo500") ?: "unknown"
                )

                players.add(
                    Player(
                        id = jsonObject.optInt("id"),
                        playerRoleId = jsonObject.optInt("playerRoleId"),
                        teamId = jsonObject.optInt("teamId"),
                        fullName = jsonObject.optString("fullName"),
                        ign = jsonObject.optString("ign"),
                        image = jsonObject.optString("image"),
                        playerRole = role,
                        team = team
                    )
                )
            } catch (e: JSONException) {
                Log.e(TAG, "Error parsing team at index $i: ${e.message}", e)
            }
        }

    }
}