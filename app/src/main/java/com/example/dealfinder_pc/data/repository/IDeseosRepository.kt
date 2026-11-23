package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.Deseo
import com.example.dealfinder_pc.modelo.DeseoRequestDTO

interface IDeseosRepository {
    suspend fun obtenerListaDeseos(): Result<List<Deseo>>
    suspend fun modificarDeseo(request: DeseoRequestDTO): Result<Unit>
    suspend fun marcarComoComprado(gameID: String): Result<Unit>
}
