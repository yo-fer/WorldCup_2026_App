package com.example.worldcup2026app.presentation.teams

import com.example.worldcup2026app.core.utils.UiText
import com.example.worldcup2026app.domain.model.Countdown
import com.example.worldcup2026app.domain.model.Team

// representación de lo que puede pasar en la pantalla de equipos
data class TeamState(
    val isLoading: Boolean = false,
    val teams: List<Team> = emptyList(),
    val error: UiText? = null,
    val countdown: Countdown?= null
)
