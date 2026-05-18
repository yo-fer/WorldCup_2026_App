package com.example.worldcup2026app.data.remote


import com.example.worldcup2026app.core.network.NetworkConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface MundialApiService {
    // Endpoint para obtener los equipos
    @GET("teams2026.php")
    suspend fun getTeams(
        @Query("league") league: String = NetworkConstants.DEFAULT_LEAGUE,
        @Query("season") season: String = NetworkConstants.DEFAULT_SEASON
    ): ApiWrapper<List<TeamResponseDto>>

    @GET("squads.php")
    suspend fun getSquad(
        @Query("team") teamId: Int
    ): ApiWrapper<List<SquadResponseDto>>
}