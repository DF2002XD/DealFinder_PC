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
            // Simulamos un retraso de comprobación en base de datos o preferencias
            _uiState.update { it.copy(isCheckUserSession = true) }
            delay(1000.milliseconds)

            // TODO: Aquí leerás si existe un token en DataStore o SharedPreferences
            val isLoggedIn = false // Cambiar a true para saltar el login

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
