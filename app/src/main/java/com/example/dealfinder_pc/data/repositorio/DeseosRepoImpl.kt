package com.example.dealfinder_pc.data.repositorio

import com.example.dealfinder_pc.modelo.AccionDeseoRequestDTO
import com.example.dealfinder_pc.modelo.DeseoDTO
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class DeseosRepoImpl : IDeseosRepo {

    override suspend fun obtenerListaDeseos(): Result<List<DeseoDTO>> {
        delay(800.milliseconds)
        val urlMock = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg"
        val mockDtos = listOf(
            DeseoDTO("1", "Silver Pines", urlMock, 22.26, 1715421200000L),
            DeseoDTO("3", "Frosthaven", urlMock, 23.19, 1715421200000L),
            DeseoDTO("4", "EA SPORTS PGA TOUR", urlMock, 6.99, 1715421200000L)
        )
        return Result.success(mockDtos)
    }

    override suspend fun modificarDeseo(request: AccionDeseoRequestDTO): Result<Unit> {
        delay(500.milliseconds)
        // Simular éxito en la API
        return Result.success(Unit)
    }

    override suspend fun marcarComoComprado(gameID: String): Result<Unit> {
        delay(500.milliseconds)
        return Result.success(Unit)
    }
}
