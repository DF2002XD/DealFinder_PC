package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la pantalla de selección de Moneda.
 *
 * @property monedasDisponibles Lista de las monedas soportadas por la API.
 * @property monedaActual Código de la moneda que el usuario tiene actualmente seleccionada.
 * @property isSaving Indica si se está guardando la preferencia en el servidor o base de datos.
 * @property errorMessage Posible mensaje de error.
 */
data class MonedasUIState(
    val monedasDisponibles: List<MonedaUIState> = emptyList(),
    val monedaActual: String = "USD",
    val isSaving: Boolean = false,
    val errorMessage: String? = null
)
