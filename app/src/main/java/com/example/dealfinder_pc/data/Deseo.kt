package com.example.dealfinder_pc.data

/**
 * Modelo de dominio para un juego guardado en la lista de deseos.
 */
data class Deseo(
    val id: String,
    val titulo: String,
    val urlImagen: String,
    val mejorPrecioActual: Double,
    val fechaAgregado: Long
)
