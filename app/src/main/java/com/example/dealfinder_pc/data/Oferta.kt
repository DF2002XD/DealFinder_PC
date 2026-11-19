package com.example.dealfinder_pc.data

/**
 * Modelo de dominio para una Oferta.
 */
data class Oferta(
    val id: String,
    val titulo: String,
    val precioRebajado: Double,
    val precioOriginal: Double,
    val descuentoPorcentaje: Double,
    val urlImagen: String,
    val nombreTienda: String,
    val puntuacionMetacritic: Int?
)
