package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.data.retrofit.network.NetworkModule
import com.example.dealfinder_pc.modelo.JuegoRespuestaDTO
import com.example.dealfinder_pc.modelo.OfertaRespuestaDTO

class OfertasApiRepo : IOfertasApiRepo {
    private val api = NetworkModule.apiService

    override suspend fun obtenerOfertas(): Result<List<OfertaRespuestaDTO>> {
        return try {
            val response = api.getOfertas()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun buscarOfertas(query: String): Result<List<OfertaRespuestaDTO>> {
        return try {
            val response = api.getOfertas(query = query)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun obtenerDetallesJuego(gameID: String): Result<JuegoRespuestaDTO> {
        return try {
            val response = api.getDetallesJuego(gameID)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
