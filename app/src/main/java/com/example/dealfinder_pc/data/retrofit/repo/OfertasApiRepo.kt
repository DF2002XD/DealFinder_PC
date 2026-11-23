package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.DealTiendaDTO
import com.example.dealfinder_pc.modelo.DetallesJuegoDTO
import com.example.dealfinder_pc.modelo.OfertaDTO
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class OfertasApiRepo : IOfertasApiRepo {

    override suspend fun obtenerOfertas(): Result<List<OfertaDTO>> {
        delay(1000.milliseconds)
        val urlMock = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg"
        val mockDtos = listOf(
            OfertaDTO("1", "Silver Pines", 22.26, 35.90, 38.0, urlMock, "Steam", 85),
            OfertaDTO("2", "Suicide Squad: Kill the Justice Le...", 3.49, 69.99, 95.0, urlMock, "Epic Games Store", 63),
            OfertaDTO("3", "Frosthaven", 23.19, 39.99, 42.0, urlMock, "GameBillet", 70),
            OfertaDTO("4", "EA SPORTS PGA TOUR", 6.99, 69.99, 90.0, urlMock, "Epic Games Store", 72),
            OfertaDTO("5", "Bad Cheese", 0.0, 12.99, 100.0, urlMock, "Epic Games Store", 63)
        )
        return Result.success(mockDtos)
    }

    override suspend fun buscarOfertas(query: String): Result<List<OfertaDTO>> {
        delay(500.milliseconds)
        val todas = obtenerOfertas().getOrNull() ?: emptyList()
        val filtradas = todas.filter { it.title.contains(query, ignoreCase = true) }
        return Result.success(filtradas)
    }

    override suspend fun obtenerDetallesJuego(gameID: String): Result<DetallesJuegoDTO> {
        delay(1000.milliseconds)
        val dto = DetallesJuegoDTO(
            gameID = gameID,
            title = "Prison Tycoon 3: Lockdown",
            thumbUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg",
            historicalLowPrice = 1.99,
            historicalLowDate = 1476057600000L,
            isOnWishlist = false,
            deals = listOf(
                DealTiendaDTO("d1", "1", "Steam", 3.49, 9.99, 65.0),
                DealTiendaDTO("d2", "2", "Fanatical", 7.48, 9.99, 25.0),
                DealTiendaDTO("d3", "3", "IndieGala", 9.99, 9.99, 0.0)
            )
        )
        return Result.success(dto)
    }
}
