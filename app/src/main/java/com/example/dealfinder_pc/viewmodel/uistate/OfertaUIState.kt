package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario (UI State) para una tarjeta de oferta.
 *
 * @property id Identificador único de la oferta.
 * @property titulo Nombre del juego o producto en oferta.
 * @property tiendaYMeta Cadena combinada que muestra el nombre de la tienda y otra información relevante (ej. Metascore), como "Steam · Meta 85".
 * @property precioOriginal Precio del producto sin rebaja, formateado como cadena (ej. "$35.90").
 * @property precioRebajado Precio del producto con la oferta aplicada, formateado como cadena (ej. "$22.26" o "GRATIS").
 * @param descuento Porcentaje del descuento aplicado, formateado como cadena (ej. "-38%").
 * @property urlImagen URL o ruta para cargar la imagen de la portada del juego.
 */
data class OfertaUIState(
    val id: String,
    val titulo: String,
    val tiendaYMeta: String,
    val precioOriginal: String,
    val precioRebajado: String,
    val descuento: String,
    val urlImagen: String
)