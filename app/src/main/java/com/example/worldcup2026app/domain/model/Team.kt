package com.example.worldcup2026app.domain.model

data class Team(
    val id: Int,
    val name: String,
    val code: String,
    val country: String,
    val foundedYear: Int?,
    val logoUrl: String,
    val groupName: String,
    val venue: Venue
)
