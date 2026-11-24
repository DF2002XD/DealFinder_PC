package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.data.retrofit.network.NetworkModule
import com.example.dealfinder_pc.modelo.AuthRespuestaDTO
import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO

class AuthApiRepo : IAuthApiRepo {
    private val api = NetworkModule.apiService

    override suspend fun iniciarSesion(request: LoginRequestDTO): Result<AuthRespuestaDTO> {
        return try {
            val response = api.login(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun registrarUsuario(request: RegistroRequestDTO): Result<AuthRespuestaDTO> {
        return try {
            val response = api.register(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string()
                println("Error en registrarUsuario: Código ${response.code()}, Cuerpo: $errorBody")
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            println("Excepción en registrarUsuario: ${e.message}")
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun recuperarContrasenha(email: String): Result<Unit> {
        return try {
            val response = api.recoverPassword(email)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error HTTP: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun cerrarSesion(): Result<Unit> {
        return try {
            val response = api.logout()
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
