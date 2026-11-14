package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario (UI State) para la pantalla de Filtros de Búsqueda.
 *
 * @property ordenSeleccionado Opción de ordenamiento seleccionada (ej. "Mejor Oferta Primero", "Precio más bajo").
 * @property tiendasDisponibles Lista de todas las tiendas disponibles para filtrar.
 * @property tiendasSeleccionadas Lista de las tiendas que el usuario ha marcado para incluir en los resultados.
 * @property precioMaximo Precio máximo que el usuario está dispuesto a pagar.
 * @property soloGratis Si se ha marcado para ver únicamente los juegos gratis.
 * @property soloEnOferta Si se ha marcado para ver únicamente los juegos con algún descuento aplicado.
 * @property isLoading Indica si se están cargando o aplicando los filtros al servidor.
 */
data class FiltrosUIState(
    val ordenSeleccionado: String = "Mejor Oferta Primero",
    val tiendasDisponibles: List<String> = emptyList(),
    val tiendasSeleccionadas: List<String> = emptyList(),
    val precioMaximo: String = "",
    val soloGratis: Boolean = false,
    val soloEnOferta: Boolean = false,
    val isLoading: Boolean = false
)
