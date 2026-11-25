package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.JuegoRespuestaDTO
import com.example.dealfinder_pc.modelo.PaginaOfertasRespuestaDTO

interface IOfertasApiRepo {
    suspend fun obtenerOfertas(query: String? = null, page: Int = 0): Result<PaginaOfertasRespuestaDTO>
    suspend fun obtenerDetallesJuego(gameID: String): Result<JuegoRespuestaDTO>
}
