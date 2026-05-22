package com.example.worldcup2026app.presentation.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldcup2026app.core.network.Resource
import com.example.worldcup2026app.core.utils.UiText
import com.example.worldcup2026app.domain.repository.PlayerRepository
import com.example.worldcup2026app.domain.usecase.GetTeamSquadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val getTeamSquadUseCase: GetTeamSquadUseCase,
    private val repository: PlayerRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow(PlayersState())
    val state: StateFlow<PlayersState> = _state.asStateFlow()

    init {
        val teamId = savedStateHandle.get<Int>("teamId") ?: -1

        if (teamId != -1) {
            observeLocalDataBase(teamId)
            syncWithApi(teamId)
        } else {
            _state.update { it.copy(error = UiText.DynamicString("Invalid id team")) }
        }
    }

    private fun observeLocalDataBase(teamId: Int) {
        viewModelScope.launch {
            getTeamSquadUseCase(teamId).collect { localPlayers ->
                _state.update { it.copy(players = localPlayers) }
            }
        }
    }

    private fun syncWithApi(teamId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            when (val result = repository.syncPlayersForTeam(teamId)) {
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
                else -> Unit
            }
        }
    }
}