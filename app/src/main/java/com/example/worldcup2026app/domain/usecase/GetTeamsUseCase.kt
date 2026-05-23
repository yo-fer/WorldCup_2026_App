package com.example.worldcup2026app.domain.usecase

import com.example.worldcup2026app.domain.model.Team
import com.example.worldcup2026app.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTeamsUseCase(
    private val repository: TeamRepository
) {
    operator fun invoke(): Flow<Map<String, List<Team>>> {
        return repository.getTeams().map { teamsList ->
            teamsList.groupBy { it.groupName }
        }
    }
}