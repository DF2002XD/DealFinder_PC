package com.example.dealfinder_pc.modelo

/**
 * DTO para representar un juego guardado en la lista de deseos del usuario.
 */
data class DeseoDTO(
    val gameID: String,
    val title: String,
    val thumbUrl: String,
    val currentBestPrice: Double,
    val dateAdded: Long
)

/**
 * DTO para enviar una petición de añadir/quitar un juego de la lista de deseos.
 */
data class AccionDeseoRequestDTO(
    val gameID: String,
    val add: Boolean // true para añadir, false para eliminar
)
