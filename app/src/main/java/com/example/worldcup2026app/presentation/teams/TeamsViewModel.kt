package com.example.worldcup2026app.presentation.teams

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldcup2026app.core.network.Resource
import com.example.worldcup2026app.domain.repository.TeamRepository
import com.example.worldcup2026app.domain.usecase.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val repository: TeamRepository
) : ViewModel() {
    // estado interno
    private val _state = MutableStateFlow(TeamState())

    // estado público (solo lectura para UI)
    val state: StateFlow<TeamState> = _state.asStateFlow()

    init {
        obserLocalDataBase()
        syncWithApi()
    }

    private fun obserLocalDataBase() {
        viewModelScope.launch {
            // recoletamos el flujo del use state
            getTeamsUseCase().collect { localTeams ->
                Log.d("MUNDIAL_TEST", "Room emitió ${localTeams.size} equipos.")
                _state.update { it.copy(teams = localTeams) }
            }
        }
    }

    private fun syncWithApi() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            // llamamos a la sincronización
            when (val result = repository.syncTeams()) {
                is Resource.Success ->{
                    Log.d("MUNDIAL_TEST", "¡Sincronización con API exitosa!")
                    _state.update { it.copy(isLoading = false) }
                }
                is Resource.Error -> {
                    Log.e("MUNDIAL_TEST", "Error en la API")
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
                else -> Unit
            }
        }
    }
}