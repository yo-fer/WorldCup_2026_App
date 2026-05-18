package com.example.worldcup2026app.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val code: String?,
    val country: String,
    val foundedYear: Int?,
    val logoUrl: String,

    @Embedded(prefix = "venue_")
    val venue: VenueEntity
)

data class VenueEntity(
    val venueId: Int,
    val venueName: String,
    val city: String,
    val capacity: Int,
    val imageUrl: String
)