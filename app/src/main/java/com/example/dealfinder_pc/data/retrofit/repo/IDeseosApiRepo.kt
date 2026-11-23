package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.AccionDeseoRequestDTO
import com.example.dealfinder_pc.modelo.DeseoDTO

interface IDeseosApiRepo {
    suspend fun obtenerListaDeseos(): Result<List<DeseoDTO>>
    suspend fun modificarDeseo(request: AccionDeseoRequestDTO): Result<Unit>
    suspend fun marcarComoComprado(gameID: String): Result<Unit>
}
