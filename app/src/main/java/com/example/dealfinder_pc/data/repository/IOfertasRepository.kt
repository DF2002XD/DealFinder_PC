package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.DetallesJuego
import com.example.dealfinder_pc.data.Oferta

interface IOfertasRepository {
    suspend fun obtenerOfertas(): Result<List<Oferta>>
    suspend fun buscarOfertas(query: String): Result<List<Oferta>>
    suspend fun obtenerDetallesJuego(gameID: String): Result<DetallesJuego>
}
