package com.example.worldcup2026app.domain.model

data class Player(
    val id: Int,
    val name: String,
    val age: Int,
    val number: Int?,
    val position: String,
    val photoUrl: String?
)
