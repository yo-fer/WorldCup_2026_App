package com.example.worldcup2026app.presentation.player

import com.example.worldcup2026app.core.utils.UiText
import com.example.worldcup2026app.domain.model.Player

data class PlayersState(
    val isLoading: Boolean = false,
    val players: List<Player> = emptyList(),
    val error: UiText? = null
)
