package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.Deseo
import com.example.dealfinder_pc.data.retrofit.repo.DeseosApiRepo
import com.example.dealfinder_pc.data.retrofit.repo.IDeseosApiRepo
import com.example.dealfinder_pc.modelo.DeseoRequestDTO

class DeseosRepository(
    private val apiRepo: IDeseosApiRepo = DeseosApiRepo()
) : IDeseosRepository {

    override suspend fun obtenerListaDeseos(): Result<List<Deseo>> {
        val result = apiRepo.obtenerListaDeseos()
        return if (result.isSuccess) {
            val dtos = result.getOrNull() ?: emptyList()
            Result.success(
                dtos.map { dto ->
                    Deseo(
                        id = dto.gameId,
                        titulo = dto.titulo,
                        urlImagen = dto.thumbUrl,
                        mejorPrecioActual = dto.precioReferencia,
                        fechaAgregado = 0L // TODO: Parse ISO date if needed
                    )
                }
            )
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Error al cargar lista de deseos"))
        }
    }

    override suspend fun modificarDeseo(request: DeseoRequestDTO): Result<Unit> {
        return apiRepo.modificarDeseo(request)
    }

    override suspend fun marcarComoComprado(gameID: String): Result<Unit> {
        return apiRepo.marcarComoComprado(gameID)
    }
}
