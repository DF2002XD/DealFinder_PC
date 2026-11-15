package com.example.dealfinder_pc.modelo

/**
 * DTO con información ampliada de un juego cuando el usuario pulsa para ver detalles.
 */
data class DetallesJuegoDTO(
    val gameID: String,
    val title: String,
    val thumbUrl: String,
    val historicalLowPrice: Double,      // Precio mínimo histórico
    val historicalLowDate: Long,         // Fecha del precio mínimo (timestamp)
    val isOnWishlist: Boolean,           // Si el usuario logueado lo tiene en su lista de deseos
    val deals: List<DealTiendaDTO>       // Lista de ofertas en distintas tiendas
)

/**
 * DTO que representa el precio de un juego en una tienda específica.
 */
data class DealTiendaDTO(
    val dealID: String,
    val storeID: String,
    val storeName: String,
    val price: Double,
    val retailPrice: Double,
    val savings: Double
)
