package com.example.dealfinder_pc.data

/**
 * Modelo de dominio con información ampliada de un juego.
 */
data class DetallesJuego(
    val id: String,
    val titulo: String,
    val urlImagen: String,
    val minimoHistoricoPrecio: Double,
    val minimoHistoricoFecha: Long,
    val estaEnDeseos: Boolean,
    val ofertasTiendas: List<DealTienda>
)

/**
 * Modelo de dominio para representar el precio de un juego en una tienda específica.
 */
data class DealTienda(
    val idOferta: String,
    val idTienda: String,
    val nombreTienda: String,
    val precioActual: Double,
    val precioNormal: Double,
    val descuentoPorcentaje: Double
)
