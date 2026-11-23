package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.JuegoRespuestaDTO
import com.example.dealfinder_pc.modelo.OfertaRespuestaDTO
import com.example.dealfinder_pc.modelo.OfertaTiendaRespuestaDTO
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class OfertasApiRepo : IOfertasApiRepo {

    override suspend fun obtenerOfertas(): Result<List<OfertaRespuestaDTO>> {
        delay(1000.milliseconds)
        val urlMock = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg"
        val mockDtos = listOf(
            OfertaRespuestaDTO("1", "g1", "Silver Pines", urlMock, "t1", "Steam", 22.26, 35.90, 38.0, 85, true, ""),
            OfertaRespuestaDTO("2", "g2", "Suicide Squad: Kill the Justice Le...", urlMock, "t2", "Epic Games Store", 3.49, 69.99, 95.0, 63, true, ""),
            OfertaRespuestaDTO("3", "g3", "Frosthaven", urlMock, "t3", "GameBillet", 23.19, 39.99, 42.0, 70, true, ""),
            OfertaRespuestaDTO("4", "g4", "EA SPORTS PGA TOUR", urlMock, "t2", "Epic Games Store", 6.99, 69.99, 90.0, 72, true, ""),
            OfertaRespuestaDTO("5", "g5", "Bad Cheese", urlMock, "t2", "Epic Games Store", 0.0, 12.99, 100.0, 63, true, "")
        )
        return Result.success(mockDtos)
    }

    override suspend fun buscarOfertas(query: String): Result<List<OfertaRespuestaDTO>> {
        delay(500.milliseconds)
        val todas = obtenerOfertas().getOrNull() ?: emptyList()
        val filtradas = todas.filter { it.titulo.contains(query, ignoreCase = true) }
        return Result.success(filtradas)
    }

    override suspend fun obtenerDetallesJuego(gameID: String): Result<JuegoRespuestaDTO> {
        delay(1000.milliseconds)
        val dto = JuegoRespuestaDTO(
            id = gameID,
            titulo = "Prison Tycoon 3: Lockdown",
            thumbUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg",
            precioHistoricoMinimo = 1.99,
            fechaHistoricoMinimo = 1476057600000L,
            enDeseos = false,
            moneda = "USD",
            ofertas = listOf(
                OfertaTiendaRespuestaDTO("d1", "1", "Steam", 3.49, 9.99, 65.0, ""),
                OfertaTiendaRespuestaDTO("d2", "2", "Fanatical", 7.48, 9.99, 25.0, ""),
                OfertaTiendaRespuestaDTO("d3", "3", "IndieGala", 9.99, 9.99, 0.0, "")
            )
        )
        return Result.success(dto)
    }
}
