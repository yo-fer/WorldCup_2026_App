package com.example.worldcup2026app.domain.repository

import com.example.worldcup2026app.core.network.Resource
import com.example.worldcup2026app.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    // Obtiene jugadores de un equipo especifico
    fun getPlayersForTeam(teamId: Int): Flow<List<Player>>

    // Fuerza descarga de jugadores desde la api
    suspend fun syncPlayersForTeam(teamId: Int): Resource<Unit>
}