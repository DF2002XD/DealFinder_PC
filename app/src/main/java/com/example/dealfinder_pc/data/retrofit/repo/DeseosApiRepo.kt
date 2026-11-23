package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.DeseoRequestDTO
import com.example.dealfinder_pc.modelo.DeseoRespuestaDTO
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class DeseosApiRepo : IDeseosApiRepo {
    override suspend fun obtenerListaDeseos(): Result<List<DeseoRespuestaDTO>> {
        delay(800.milliseconds)
        val urlMock = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg"
        val mockDtos = listOf(
            DeseoRespuestaDTO(1L, "1", "Silver Pines", urlMock, 22.26, "2024-05-11T12:00:00"),
            DeseoRespuestaDTO(2L, "3", "Frosthaven", urlMock, 23.19, "2024-05-11T12:00:00"),
            DeseoRespuestaDTO(3L, "4", "EA SPORTS PGA TOUR", urlMock, 6.99, "2024-05-11T12:00:00")
        )
        return Result.success(mockDtos)
    }

    override suspend fun modificarDeseo(request: DeseoRequestDTO): Result<Unit> {
        delay(500.milliseconds)
        return Result.success(Unit)
    }

    override suspend fun marcarComoComprado(gameID: String): Result<Unit> {
        delay(500.milliseconds)
        return Result.success(Unit)
    }
}
