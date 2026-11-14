package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la pantalla principal de Ofertas.
 *
 * @property ofertas Lista de las ofertas actuales devueltas por la API.
 * @property busquedaActual Texto que el usuario ha escrito en la barra de búsqueda.
 * @property monedaActual Moneda en la que se muestran los precios (ej. "USD").
 * @property textoFiltroActivo Resumen de los filtros aplicados (ej. "Todas las ofertas · mejor oferta primero").
 * @property isLoading Indica si se está realizando una búsqueda o cargando las ofertas desde la API.
 * @property errorMessage Posible mensaje de error al fallar la carga.
 */
data class OfertasUIState(
    val ofertas: List<OfertaUIState> = emptyList(),
    val busquedaActual: String = "",
    val monedaActual: String = "USD",
    val textoFiltroActivo: String = "Todas las ofertas · mejor oferta primero",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
