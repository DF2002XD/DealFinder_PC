package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario (UI State) para la pantalla de Cuenta del usuario.
 *
 * @property nombreUsuario Nombre actual del usuario autenticado.
 * @property correo Correo electrónico del usuario autenticado.
 * @property isCambiandoContrasenha Indica si se está enviando la solicitud para cambiar la contraseña en segundo plano.
 * @property errorMessage Mensaje de error si la acción de la cuenta falla.
 */
data class CuentaUIState(
    val nombreUsuario: String = "",
    val correo: String = "",
    val isCambiandoContrasenha: Boolean = false,
    val errorMessage: String? = null
)
