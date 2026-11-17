package com.example.dealfinder_pc.data.repositorio

import com.example.dealfinder_pc.modelo.AccionDeseoRequestDTO
import com.example.dealfinder_pc.modelo.DeseoDTO

/**
 * Interfaz para el repositorio de la lista de deseos del usuario.
 */
interface IDeseosRepo {
    suspend fun obtenerListaDeseos(): Result<List<DeseoDTO>>
    suspend fun modificarDeseo(request: AccionDeseoRequestDTO): Result<Unit>
    suspend fun marcarComoComprado(gameID: String): Result<Unit>
}
