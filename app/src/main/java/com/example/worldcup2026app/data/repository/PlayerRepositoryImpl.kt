package com.example.worldcup2026app.data.repository


import com.example.worldcup2026app.R
import com.example.worldcup2026app.core.network.Resource
import com.example.worldcup2026app.core.utils.UiText
import com.example.worldcup2026app.data.local.dao.MundialDao
import com.example.worldcup2026app.data.mapper.toDomain
import com.example.worldcup2026app.data.mapper.toEntity
import com.example.worldcup2026app.data.remote.MundialApiService
import com.example.worldcup2026app.domain.model.Player
import com.example.worldcup2026app.domain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class PlayerRepositoryImpl(
    private val api: MundialApiService,
    private val dao: MundialDao
) : PlayerRepository {
    override fun getPlayersForTeam(teamId: Int): Flow<List<Player>> {
        return dao.getPlayersForTeam(teamId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun syncPlayersForTeam(teamId: Int): Resource<Unit> {
        return try {
            // pasamos el teamId a la API
            val result = api.getSquad(teamId)

            // Buscamos el primer elemento (el equipo) y tomamos su lista de jugadores
            val playersDto = result.response.firstOrNull()?.players ?: emptyList()

            // Transformamos los DTOs inyectándoles el teamId
            val entities = playersDto.map { it.toEntity(teamId) }

            dao.insertPlayers(entities)

            Resource.Success(Unit)

        } catch (e: IOException) {
            Resource.Error(
                message = UiText.StringResource(R.string.error_connectivity)
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = UiText.StringResource(R.string.error_server, e.code())
            )
        } catch (e: Exception) {
            Resource.Error(
                message = UiText.StringResource(R.string.error_unknown, e.localizedMessage ?: "Unknown")
            )
        }
    }


}