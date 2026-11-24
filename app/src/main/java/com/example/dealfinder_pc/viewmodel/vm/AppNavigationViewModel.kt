package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.viewmodel.uistate.AppNavigationUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class AppNavigationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppNavigationUIState())
    val uiState: StateFlow<AppNavigationUIState> = _uiState.asStateFlow()

    init {
        comprobarSesion()
    }

    private fun comprobarSesion() {
        viewModelScope.launch {
            _uiState.update { it.copy(isCheckUserSession = true) }
            
            // Un pequeño delay para que dé tiempo a leer el SharedPreferences
            // y para que no parpadee la pantalla muy bruscamente
            delay(500.milliseconds)

            // Leemos si hay un token persistente guardado en el archivo
            val tokenGuardado = com.example.dealfinder_pc.TokenManager.getToken()
            val isLoggedIn = !tokenGuardado.isNullOrBlank()

            _uiState.update {
                it.copy(
                    isCheckUserSession = false,
                    isUserLoggedIn = isLoggedIn,
                    initialRoute = if (isLoggedIn) "ofertas" else "login"
                )
            }
        }
    }
}
