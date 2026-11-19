package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.Usuario
import com.example.dealfinder_pc.data.retrofit.repo.AuthApiRepo
import com.example.dealfinder_pc.data.retrofit.repo.IAuthApiRepo
import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO

class AuthRepository(
    private val apiRepo: IAuthApiRepo = AuthApiRepo()
) : IAuthRepository {

    override suspend fun iniciarSesion(request: LoginRequestDTO): Result<Usuario> {
        val result = apiRepo.iniciarSesion(request)
        return if (result.isSuccess) {
            val dto = result.getOrNull()
            if (dto != null) {
                Result.success(
                    Usuario(
                        id = dto.userId,
                        nombreUsuario = dto.username,
                        correo = dto.email,
                        token = dto.token,
                        presupuestoMensual = dto.monthlyBudget,
                        monedaPreferida = dto.preferredCurrency,
                        tiendasPreferidas = dto.preferredStores,
                        alertasActivadas = dto.dealsAlertsEnabled
                    )
                )
            } else {
                Result.failure(Exception("Usuario no encontrado"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Error en inicio de sesión"))
        }
    }

    override suspend fun registrarUsuario(request: RegistroRequestDTO): Result<Usuario> {
        val result = apiRepo.registrarUsuario(request)
        return if (result.isSuccess) {
            val dto = result.getOrNull()
            if (dto != null) {
                Result.success(
                    Usuario(
                        id = dto.userId,
                        nombreUsuario = dto.username,
                        correo = dto.email,
                        token = dto.token,
                        presupuestoMensual = dto.monthlyBudget,
                        monedaPreferida = dto.preferredCurrency,
                        tiendasPreferidas = dto.preferredStores,
                        alertasActivadas = dto.dealsAlertsEnabled
                    )
                )
            } else {
                Result.failure(Exception("Error en registro"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Error en registro"))
        }
    }

    override suspend fun recuperarContrasenha(email: String): Result<Unit> {
        return apiRepo.recuperarContrasenha(email)
    }

    override suspend fun cerrarSesion(): Result<Unit> {
        return apiRepo.cerrarSesion()
    }
}
