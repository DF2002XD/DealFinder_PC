package com.example.dealfinder_pc.modelo

data class OfertaRespuestaDTO(
    val dealId: String,
    val gameId: String,
    val titulo: String,
    val thumbUrl: String,
    val tiendaId: String,
    val tiendaNombre: String,
    val precioOferta: Double,
    val precioNormal: Double,
    val descuento: Double,
    val metacritic: Int?,
    val enOferta: Boolean,
    val enlace: String
)

data class PaginaOfertasRespuestaDTO(
    val ofertas: List<OfertaRespuestaDTO>,
    val pagina: Int,
    val tamanio: Int,
    val totalPaginas: Int,
    val hayMas: Boolean,
    val moneda: String
)

data class JuegoRespuestaDTO(
    val id: String,
    val titulo: String,
    val thumbUrl: String,
    val precioHistoricoMinimo: Double,
    val fechaHistoricoMinimo: Long,
    val enDeseos: Boolean,
    val moneda: String,
    val ofertas: List<OfertaTiendaRespuestaDTO>
)

data class OfertaTiendaRespuestaDTO(
    val dealId: String,
    val tiendaId: String,
    val tiendaNombre: String,
    val precioOferta: Double,
    val precioNormal: Double,
    val descuento: Double,
    val enlace: String
)

data class TiendaRespuestaDTO(
    val id: String,
    val nombre: String,
    val iconoUrl: String?
)
