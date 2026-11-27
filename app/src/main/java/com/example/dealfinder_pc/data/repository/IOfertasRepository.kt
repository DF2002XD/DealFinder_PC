package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.DetallesJuego
import com.example.dealfinder_pc.data.PaginaOferta
import com.example.dealfinder_pc.data.Tienda

interface IOfertasRepository {
    suspend fun obtenerOfertas(query: String? = null, maxPrice: Double? = null, onSale: Boolean? = null, sortBy: String? = null, storeIds: String? = null, page: Int = 0): Result<PaginaOferta>
    suspend fun obtenerDetallesJuego(gameID: String): Result<DetallesJuego>
    suspend fun obtenerTiendas(): Result<List<Tienda>>
}
