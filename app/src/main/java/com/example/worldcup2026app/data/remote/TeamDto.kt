package com.example.worldcup2026app.data.remote

import com.google.gson.annotations.SerializedName

data class TeamResponseDto(
    @SerializedName("team") val team: TeamDto,
    @SerializedName("venue") val venue: VenueDto
)

data class TeamDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("code") val code: String?,
    @SerializedName("country") val country: String,
    @SerializedName("founded") val founded: Int?,
    @SerializedName("logo") val logoUrl: String
)

data class VenueDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("city") val city: String,
    @SerializedName("capacity") val capacity: Int,
    @SerializedName("image") val imageUrl: String
)