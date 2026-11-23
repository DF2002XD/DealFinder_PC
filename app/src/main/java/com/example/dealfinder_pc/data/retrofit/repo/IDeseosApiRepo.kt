package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.DeseoRequestDTO
import com.example.dealfinder_pc.modelo.DeseoRespuestaDTO

interface IDeseosApiRepo {
    suspend fun obtenerListaDeseos(): Result<List<DeseoRespuestaDTO>>
    suspend fun modificarDeseo(request: DeseoRequestDTO): Result<Unit>
    suspend fun marcarComoComprado(gameID: String): Result<Unit>
}
