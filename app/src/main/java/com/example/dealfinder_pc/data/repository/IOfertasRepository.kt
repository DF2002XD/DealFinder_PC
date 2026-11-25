package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.DetallesJuego
import com.example.dealfinder_pc.data.PaginaOferta

interface IOfertasRepository {
    suspend fun obtenerOfertas(query: String? = null, page: Int = 0): Result<PaginaOferta>
    suspend fun obtenerDetallesJuego(gameID: String): Result<DetallesJuego>
}
