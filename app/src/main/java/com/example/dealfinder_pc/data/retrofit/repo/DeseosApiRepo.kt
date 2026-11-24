package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.data.retrofit.network.NetworkModule
import com.example.dealfinder_pc.modelo.DeseoRequestDTO
import com.example.dealfinder_pc.modelo.DeseoRespuestaDTO

class DeseosApiRepo : IDeseosApiRepo {
    private val api = NetworkModule.apiService

    override suspend fun obtenerListaDeseos(): Result<List<DeseoRespuestaDTO>> {
        return try {
            val response = api.getWishlist()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun modificarDeseo(request: DeseoRequestDTO): Result<Unit> {
        return try {
            val response = api.toggleWishlistItem(request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun marcarComoComprado(gameID: String): Result<Unit> {
        return try {
            val response = api.markAsPurchased(gameID)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
