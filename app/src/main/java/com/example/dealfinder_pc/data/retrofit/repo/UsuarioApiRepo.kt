package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.data.retrofit.network.NetworkModule
import com.example.dealfinder_pc.modelo.ActualizarPerfilRequestDTO
import com.example.dealfinder_pc.modelo.CambiarContrasenaRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioRespuestaDTO

class UsuarioApiRepo : IUsuarioApiRepo {
    private val api = NetworkModule.apiService

    override suspend fun obtenerPerfil(): Result<UsuarioRespuestaDTO> {
        return try {
            val response = api.getUserProfile()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                println("Error en obtenerPerfil: Código ${response.code()}, Cuerpo: ${response.errorBody()?.string()}")
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            println("Excepción en obtenerPerfil: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun actualizarPreferencias(request: ActualizarPerfilRequestDTO): Result<Unit> {
        return try {
            val response = api.updatePreferences(request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                println("Error en actualizarPreferencias: Código ${response.code()}, Cuerpo: ${response.errorBody()?.string()}")
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            println("Excepción en actualizarPreferencias: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun cambiarCorreo(nuevoCorreo: String): Result<Unit> {
        return try {
            val response = api.updateEmail(nuevoCorreo)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                println("Error en cambiarCorreo: Código ${response.code()}, Cuerpo: ${response.errorBody()?.string()}")
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun cambiarContrasenha(actual: String, nueva: String): Result<Unit> {
        return try {
            val request = CambiarContrasenaRequestDTO(actual, nueva)
            val response = api.updatePassword(request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                println("Error en cambiarContrasenha: Código ${response.code()}, Cuerpo: ${response.errorBody()?.string()}")
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
