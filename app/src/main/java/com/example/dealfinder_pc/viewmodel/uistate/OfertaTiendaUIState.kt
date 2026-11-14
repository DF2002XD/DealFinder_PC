package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa los datos de una tienda individual dentro de los detalles de un juego.
 * @property descuentoTexto Texto del descuento, si lo hay.
 * @property esLaMejor Si es la mejor oferta de la lista.
 * @property urlLogo URL de la imagen del logo de la tienda (opcional).
 */
data class OfertaTiendaUIState(
    val nombreTienda: String,
    val precio: String,
    val descuentoTexto: String? = null, // ej: "-65%"
    val esLaMejor: Boolean = false,
    val urlLogo: String? = null
)