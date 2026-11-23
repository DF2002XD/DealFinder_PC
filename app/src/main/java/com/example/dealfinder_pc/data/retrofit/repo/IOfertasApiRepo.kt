package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.JuegoRespuestaDTO
import com.example.dealfinder_pc.modelo.OfertaRespuestaDTO

interface IOfertasApiRepo {
    suspend fun obtenerOfertas(): Result<List<OfertaRespuestaDTO>>
    suspend fun buscarOfertas(query: String): Result<List<OfertaRespuestaDTO>>
    suspend fun obtenerDetallesJuego(gameID: String): Result<JuegoRespuestaDTO>
}
