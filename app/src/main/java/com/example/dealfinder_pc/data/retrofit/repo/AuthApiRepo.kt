package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioResponseDTO
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class AuthApiRepo : IAuthApiRepo {
    override suspend fun iniciarSesion(request: LoginRequestDTO): Result<UsuarioResponseDTO> {
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
