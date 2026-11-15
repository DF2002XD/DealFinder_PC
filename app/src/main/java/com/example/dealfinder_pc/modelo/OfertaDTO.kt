package com.example.dealfinder_pc.modelo

/**
 * DTO que representa una oferta individual de un juego devuelta por nuestro servidor Spring Boot
 * (el cual internamente la extrae de CheapShark).
 */
data class OfertaDTO(
    val gameID: String,          // ID interno de CheapShark / Tu BD
    val title: String,           // Título del juego
    val salePrice: Double,       // Precio de oferta actual
    val normalPrice: Double,     // Precio original sin descuento
    val savings: Double,         // Porcentaje de ahorro (ej. 75.0)
    val thumbUrl: String,        // URL de la carátula
    val storeID: String,         // ID de la tienda con la mejor oferta
    val metacriticScore: Int?    // Nota de metacritic (opcional)
)
