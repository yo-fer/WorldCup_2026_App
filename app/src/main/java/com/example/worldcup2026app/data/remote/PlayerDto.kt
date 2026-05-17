package com.example.worldcup2026app.data.remote

import com.google.gson.annotations.SerializedName

data class SquadResponseDto(
    @SerializedName("team") val team: TeamDto,
    @SerializedName("players") val players: List<PlayerDto>
)

data class PlayerDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("number") val number: Int?,
    @SerializedName("position") val position: String,
    @SerializedName("photo") val photoUrl: String?
)
