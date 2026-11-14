package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario (UI State) para la pantalla de Detalles de un Juego.
 *
 * @property idOferta ID único de la oferta o del juego.
 * @property tituloJuego Título del videojuego.
 * @property urlImagen URL de la carátula o póster del juego para cargar vía Coil.
 * @property minimoHistoricoInfo Información descriptiva del mínimo histórico (fecha, evento, etc.).
 * @property precioMinimoHistorico El valor monetario del mínimo histórico formateado (ej. "$1.99").
 * @property precioNormal Precio base o sin descuento (ej. "$9.99").
 * @property ofertasEnTiendas Lista con las ofertas disponibles en diferentes tiendas para este juego.
 * @property isLoading Indica si se están descargando los datos del juego desde la API.
 * @property errorMessage Posible mensaje de error en caso de que falle la carga.
 */
data class DetallesUIState(
    val idOferta: String = "",
    val tituloJuego: String = "",
    val urlImagen: String = "",
    val minimoHistoricoInfo: String = "",
    val precioMinimoHistorico: String = "",
    val precioNormal: String = "",
    val ofertasEnTiendas: List<OfertaTiendaUIState> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
