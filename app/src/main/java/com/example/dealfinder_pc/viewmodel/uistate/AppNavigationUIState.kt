package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la lógica de navegación de la aplicación.
 *
 * @property isCheckUserSession Indica si la aplicación está verificando actualmente la sesión activa del usuario al arrancar.
 * @property isUserLoggedIn Indica si el usuario ha iniciado sesión correctamente (para saltarse el Login).
 * @property initialRoute Define la ruta inicial basada en si el usuario está logueado o no.
 * @property userId Identificador único del usuario actual.
 */
data class AppNavigationUIState(
    val isCheckUserSession: Boolean = true,
    val isUserLoggedIn: Boolean = false,
    val initialRoute: String = "login",
    val userId: String? = null
)