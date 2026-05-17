package com.example.worldcup2026app.domain.usecase

import com.example.worldcup2026app.domain.model.Team
import com.example.worldcup2026app.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow

class GetTeamsUseCase(
    private val repository: TeamRepository
) {
    operator fun invoke(): Flow<List<Team>> {
        return repository.getTeams()
    }
}