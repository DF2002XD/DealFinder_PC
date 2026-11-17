package com.example.dealfinder_pc.data.repositorio

import com.example.dealfinder_pc.modelo.DetallesJuegoDTO
import com.example.dealfinder_pc.modelo.OfertaDTO

/**
 * Interfaz para el repositorio de ofertas de videojuegos.
 */
interface IOfertasRepo {
    suspend fun obtenerOfertas(): Result<List<OfertaDTO>>
    suspend fun buscarOfertas(query: String): Result<List<OfertaDTO>>
    suspend fun obtenerDetallesJuego(gameID: String): Result<DetallesJuegoDTO>
}
