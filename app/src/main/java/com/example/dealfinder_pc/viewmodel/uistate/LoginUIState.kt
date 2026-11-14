package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario (UI State) para la pantalla de Login.
 *
 * @property correo Correo electrónico introducido por el usuario.
 * @property contrasenha Contraseña introducida por el usuario.
 * @property keepLogged Indica si la casilla de "Mantener sesión iniciada" está marcada.
 * @property isLoading Indica si se está realizando la autenticación en el servidor.
 * @property errorMessage Mensaje de error a mostrar si el inicio de sesión falla.
 */
data class LoginUIState(
    val correo: String = "",
    val contrasenha: String = "",
    val keepLogged: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
