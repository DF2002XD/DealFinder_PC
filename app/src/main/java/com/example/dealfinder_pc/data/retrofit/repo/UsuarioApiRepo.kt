package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.PreferenciaUpdateRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioResponseDTO
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class UsuarioApiRepo : IUsuarioApiRepo {
    override suspend fun obtenerPerfil(): Result<UsuarioResponseDTO> {
        delay(500.milliseconds)
        val dto = UsuarioResponseDTO(
            userId = "uuid-1234",
            username = "JUGADOR_01",
            email = "jugador@correo.com",
            token = "fake_jwt",
            monthlyBudget = 100.0,
            preferredCurrency = "USD",
            preferredStores = listOf("Steam", "GOG"),
            dealsAlertsEnabled = true
        )
        return Result.success(dto)
    }

    override suspend fun actualizarPreferencias(request: PreferenciaUpdateRequestDTO): Result<Unit> {
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
