package com.example.worldcup2026app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "players",
    // llave foránea
    // no puede haber un jugador cuyo teamId no exista en la tabla teams
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["teamId"],
            onDelete = ForeignKey.CASCADE // Si se borra un equipo, se borran sus jugadores automáticamente
        )
    ],
    indices = [Index(value = ["teamId"])] // Indexamos para que buscar jugadores por equipo sea ultra rápido
)
data class PlayerEntity(
    @PrimaryKey val id: Int,
    val teamId: Int,
    val name: String,
    val age: Int,
    val jerseyNumber: Int?,
    val position: String,
    val photoUrl: String?
)
