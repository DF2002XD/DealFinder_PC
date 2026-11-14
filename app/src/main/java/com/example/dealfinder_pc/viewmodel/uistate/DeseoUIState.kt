package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario (UI State) para un elemento individual dentro de la Lista de Deseos.
 *
 * @property id Identificador único del juego o producto deseado.
 * @property titulo Nombre del juego o producto.
 * @property precioRebajado Precio actual con descuento aplicado, formateado como cadena (ej. "$22.26").
 * @property urlImagen URL o ruta para cargar la imagen de la portada del juego.
 * @property textoBoton Texto a mostrar en el botón de acción de la tarjeta. Por defecto es "COMPRADO".
 */
data class DeseoUIState(
    val id: String,
    val titulo: String,
    val precioRebajado: String,
    val urlImagen: String,
    val textoBoton: String = "COMPRADO"
)
