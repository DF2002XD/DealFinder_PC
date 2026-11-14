package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario (UI State) para la pantalla de Registro.
 *
 * @property nombreUsuario Nombre elegido por el usuario para su perfil.
 * @property correo Correo electrónico introducido por el usuario.
 * @property contrasenha Contraseña introducida por el usuario.
 * @property confirmarContrasenha Confirmación de la contraseña introducida por el usuario.
 * @property isLoading Indica si se está realizando el registro en el servidor.
 * @property errorMessage Mensaje de error a mostrar si el registro falla (ej. contraseñas no coinciden).
 */
data class RegistroUIState(
    val nombreUsuario: String = "",
    val correo: String = "",
    val contrasenha: String = "",
    val confirmarContrasenha: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
