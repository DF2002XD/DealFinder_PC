package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.JuegoRespuestaDTO
import com.example.dealfinder_pc.modelo.PaginaOfertasRespuestaDTO
import com.example.dealfinder_pc.modelo.TiendaRespuestaDTO

interface IOfertasApiRepo {
    suspend fun obtenerOfertas(query: String? = null, maxPrice: Double? = null, onSale: Boolean? = null, sortBy: String? = null, storeIds: String? = null, page: Int = 0): Result<PaginaOfertasRespuestaDTO>
    suspend fun obtenerDetallesJuego(gameID: String): Result<JuegoRespuestaDTO>
    suspend fun obtenerTiendas(): Result<List<TiendaRespuestaDTO>>
}
