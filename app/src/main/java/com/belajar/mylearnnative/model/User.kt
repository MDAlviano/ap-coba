package com.belajar.mylearnnative.model

data class User(
    val id: String,
    val fullName: String,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val image: String?
)
