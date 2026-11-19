package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.Deseo
import com.example.dealfinder_pc.modelo.AccionDeseoRequestDTO

interface IDeseosRepository {
    suspend fun obtenerListaDeseos(): Result<List<Deseo>>
    suspend fun modificarDeseo(request: AccionDeseoRequestDTO): Result<Unit>
    suspend fun marcarComoComprado(gameID: String): Result<Unit>
}
