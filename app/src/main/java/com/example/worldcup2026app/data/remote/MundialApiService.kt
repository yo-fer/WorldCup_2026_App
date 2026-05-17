package com.example.worldcup2026app.data.remote


import retrofit2.http.GET
import retrofit2.http.Query

interface MundialApiService {
    // Endpoint para obtener los equipos
    @GET("teams2026.php")
    suspend fun getTeams(
        @Query("league") league: String = "1",
        @Query("season") season: String = "2026"
    ): ApiWrapper<List<TeamResponseDto>>

    @GET("squads.php")
    suspend fun getSquad(
        @Query("team") teamId: Int
    ): ApiWrapper<List<SquadResponseDto>>
}