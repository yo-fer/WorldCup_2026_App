package com.example.worldcup2026app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worldcup2026app.data.local.entity.TeamEntity
import com.example.worldcup2026app.data.local.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MundialDao {

    // --- OPERACIONES DE EQUIPOS ---

    @Query("SELECT * FROM teams ORDER BY name ASC")
    fun getAllTeams(): Flow<List<TeamEntity>> // Retorna un flujo reactivo de la BD

    @Query("SELECT * FROM teams WHERE id = :teamId")
    suspend fun getTeamById(teamId: Int): TeamEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamEntity>)

    // --- OPERACIONES DE JUGADORES ---

    @Query("SELECT * FROM players WHERE teamId = :teamId")
    fun getPlayersForTeam(teamId: Int): Flow<List<PlayerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayers(players: List<PlayerEntity>)
}