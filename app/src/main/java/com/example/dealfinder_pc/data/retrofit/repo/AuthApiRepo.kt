package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.AuthRespuestaDTO
import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioRespuestaDTO
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class AuthApiRepo : IAuthApiRepo {
    override suspend fun iniciarSesion(request: LoginRequestDTO): Result<AuthRespuestaDTO> {
        delay(1500.milliseconds)
        val dto = AuthRespuestaDTO(
            accessToken = "fake-jwt-token",
            refreshToken = "fake-refresh-token",
            expiraEnSegundos = 3600,
            usuario = UsuarioRespuestaDTO(
                id = 1L,
                nombreUsuario = "JUGADOR_TEST",
                correo = request.correo,
                moneda = "USD",
                avisosOfertas = false
            )
        )
        return Result.success(dto)
    }

    override suspend fun registrarUsuario(request: RegistroRequestDTO): Result<AuthRespuestaDTO> {
        delay(1500.milliseconds)
        val dto = AuthRespuestaDTO(
            accessToken = "fake-jwt-token",
            refreshToken = "fake-refresh-token",
            expiraEnSegundos = 3600,
            usuario = UsuarioRespuestaDTO(
                id = 2L,
                nombreUsuario = request.nombreUsuario,
                correo = request.correo,
                moneda = "USD",
                avisosOfertas = false
            )
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
