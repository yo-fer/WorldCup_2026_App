package com.example.worldcup2026app.data.repository

import com.example.worldcup2026app.R
import com.example.worldcup2026app.core.network.Resource
import com.example.worldcup2026app.core.utils.UiText
import com.example.worldcup2026app.data.local.dao.MundialDao
import com.example.worldcup2026app.data.mapper.toDomain
import com.example.worldcup2026app.data.mapper.toEntity
import com.example.worldcup2026app.data.remote.MundialApiService
import com.example.worldcup2026app.domain.model.Team
import com.example.worldcup2026app.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

// recibe la API y la DB a través del constructor
class TeamRepositoryImpl(
    private val api: MundialApiService,
    private val dao: MundialDao
) : TeamRepository {
    // conecta la DB local con la UI transformando las entidades
    // a modelos limpios
    override fun getTeams(): Flow<List<Team>> {
        return dao.getAllTeams().map { entities ->
            entities.map { it.toDomain() }

        }
    }

    // lectura simple para los equipos
    override suspend fun getTeamById(teamId: Int): Team? {
        return dao.getTeamById(teamId)?.toDomain()
    }

    // sincronización: baja de internat y guarda en local
    override suspend fun syncTeams(): Resource<Unit> {
        return try {
            // hacemos la petición a la API
            val result = api.getTeams()

            // transformamos los dtos que llegaron a entidades de BD
            val entities = result.response.map { it.toEntity() }

            // Se guarda en Room
            dao.insertTeams(entities)

            // Enviar mensaje de éxito
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