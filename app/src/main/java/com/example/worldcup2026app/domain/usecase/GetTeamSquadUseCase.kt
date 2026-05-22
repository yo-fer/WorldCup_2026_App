package com.example.worldcup2026app.domain.usecase

import com.example.worldcup2026app.domain.model.Player
import com.example.worldcup2026app.domain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow

class GetTeamSquadUseCase(
    private val repository: PlayerRepository
) {
    operator fun invoke(teamId: Int): Flow<List<Player>> {
        return repository.getPlayersForTeam(teamId)
    }
}