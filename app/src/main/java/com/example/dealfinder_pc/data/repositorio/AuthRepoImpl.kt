package com.example.dealfinder_pc.data.repositorio

import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioResponseDTO
import com.example.dealfinder_pc.data.retrofit.NetworkModule
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class AuthRepoImpl : IAuthRepo {
    private val api = NetworkModule.apiService

    override suspend fun iniciarSesion(request: LoginRequestDTO): Result<UsuarioResponseDTO> {
        // --- CÓDIGO REAL (Comentado) ---
        /*
        return try {
            val response = api.login(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Credenciales inválidas"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        */

        // --- MOCK TEMPORAL ---
        delay(1500.milliseconds)
        val dto = UsuarioResponseDTO(
            userId = "test-id",
            username = "JUGADOR_TEST",
            email = request.email,
            token = "fake-jwt-token",
            monthlyBudget = null,
            preferredCurrency = "USD",
            preferredStores = emptyList(),
            dealsAlertsEnabled = false
        )
        return Result.success(dto)
    }

    override suspend fun registrarUsuario(request: RegistroRequestDTO): Result<UsuarioResponseDTO> {
        // --- CÓDIGO REAL (Comentado) ---
        /*
        return try {
            val response = api.register(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("El usuario ya existe o error en servidor"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        */
        
        delay(1500.milliseconds)
        val dto = UsuarioResponseDTO(
            userId = "new-id",
            username = request.username,
            email = request.email,
            token = "fake-jwt-token",
            monthlyBudget = null,
            preferredCurrency = "USD",
            preferredStores = emptyList(),
            dealsAlertsEnabled = false
        )
        return Result.success(dto)
    }

    override suspend fun recuperarContrasenha(email: String): Result<Unit> {
        delay(1500.milliseconds)
        return Result.success(Unit)
    }

    override suspend fun cerrarSesion(): Result<Unit> {
        delay(500.milliseconds)
        return Result.success(Unit)
    }
}
