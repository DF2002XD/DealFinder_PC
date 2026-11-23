package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.ActualizarPerfilRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioRespuestaDTO
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class UsuarioApiRepo : IUsuarioApiRepo {
    override suspend fun obtenerPerfil(): Result<UsuarioRespuestaDTO> {
        delay(500.milliseconds)
        val dto = UsuarioRespuestaDTO(
            id = 1234L,
            nombreUsuario = "JUGADOR_01",
            correo = "jugador@correo.com",
            moneda = "USD",
            avisosOfertas = true
        )
        return Result.success(dto)
    }

    override suspend fun actualizarPreferencias(request: ActualizarPerfilRequestDTO): Result<Unit> {
        delay(1000.milliseconds)
        return Result.success(Unit)
    }

    override suspend fun cambiarCorreo(nuevoCorreo: String): Result<Unit> {
        delay(1500.milliseconds)
        return Result.success(Unit)
    }

    override suspend fun cambiarContrasenha(actual: String, nueva: String): Result<Unit> {
        delay(1500.milliseconds)
        return Result.success(Unit)
    }
}
