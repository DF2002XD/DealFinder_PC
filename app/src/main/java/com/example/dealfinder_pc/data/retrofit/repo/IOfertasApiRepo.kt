package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.DetallesJuegoDTO
import com.example.dealfinder_pc.modelo.OfertaDTO

interface IOfertasApiRepo {
    suspend fun obtenerOfertas(): Result<List<OfertaDTO>>
    suspend fun buscarOfertas(query: String): Result<List<OfertaDTO>>
    suspend fun obtenerDetallesJuego(gameID: String): Result<DetallesJuegoDTO>
}
