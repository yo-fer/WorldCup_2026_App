package com.example.worldcup2026app.domain.repository

import com.example.worldcup2026app.core.network.Resource
import com.example.worldcup2026app.domain.model.Team
import kotlinx.coroutines.flow.Flow


interface TeamRepository {
    fun getTeams(): Flow<List<Team>>

    // Obtener un solo equipo
    suspend fun getTeamById(teamId: Int): Team?

    // Fuerza la descarga desde la api y devuelve un resource para ver si hubo error
    suspend fun syncTeams(): Resource<Unit>
}