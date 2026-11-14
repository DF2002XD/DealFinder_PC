package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la pantalla de la Lista de Deseos.
 *
 * @property juegosEnDeseos Lista de los juegos que el usuario ha guardado en su lista.
 * @property precioTotalEstimado Texto que resume la cantidad de juegos y su precio total aproximado.
 * @property isLoading Indica si se está cargando la lista desde la base de datos o actualizando precios.
 * @property errorMessage Posible mensaje de error.
 */
data class DeseosUIState(
    val juegosEnDeseos: List<DeseoUIState> = emptyList(),
    val precioTotalEstimado: String = "0 juegos · aprox. $0.00",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
