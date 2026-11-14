package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario (UI State) para la pantalla de Recuperación de Contraseña.
 *
 * @property correo Correo electrónico donde se enviarán las instrucciones.
 * @property isLoading Indica si se está enviando el correo de recuperación al servidor.
 * @property isSuccess Indica si el correo se envió con éxito para poder mostrar un mensaje positivo o salir de la pantalla.
 * @property errorMessage Mensaje de error a mostrar si el envío falla.
 */
data class RecuperarContraUIState(
    val correo: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
