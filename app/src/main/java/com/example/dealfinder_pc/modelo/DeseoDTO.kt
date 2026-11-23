package com.example.dealfinder_pc.modelo

data class DeseoRespuestaDTO(
    val id: Long,
    val gameId: String,
    val titulo: String,
    val thumbUrl: String,
    val precioReferencia: Double,
    val fechaAlta: String // Viene como cadena ISO desde Spring Boot (LocalDateTime)
)

data class DeseoRequestDTO(
    val gameId: String,
    val titulo: String,
    val thumbUrl: String,
    val precioReferencia: Double
)
