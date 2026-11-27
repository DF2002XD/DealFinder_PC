package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.Usuario
import com.example.dealfinder_pc.data.retrofit.network.NetworkModule
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
                // 1. Guardamos SIEMPRE el token en memoria (RAM)
                NetworkModule.authToken = dto.accessToken
                
                // 2. Si el usuario marcó "Mantener sesión", lo guardamos TAMBIÉN en persistencia (XML)
                if (request.mantenerSesion) {
                    com.example.dealfinder_pc.TokenManager.saveToken(dto.accessToken)
                } else {
                    // Si NO lo marcó, nos aseguramos de borrar cualquier rastro de sesiones pasadas en disco
                    com.example.dealfinder_pc.TokenManager.clearToken()
                }
                
                Result.success(
                    Usuario(
                        id = dto.usuario.id.toString(),
                        nombreUsuario = dto.usuario.nombreUsuario,
                        correo = dto.usuario.correo,
                        token = dto.accessToken,
                        presupuestoMensual = null,
                        monedaPreferida = dto.usuario.moneda,
                        tiendasPreferidas = emptyList(),
                        alertasActivadas = dto.usuario.avisosOfertas
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
                // Guardamos el token en memoria (RAM) para las siguientes peticiones
                NetworkModule.authToken = dto.accessToken
                
                Result.success(
                    Usuario(
                        id = dto.usuario.id.toString(),
                        nombreUsuario = dto.usuario.nombreUsuario,
                        correo = dto.usuario.correo,
                        token = dto.accessToken,
                        presupuestoMensual = null,
                        monedaPreferida = dto.usuario.moneda,
                        tiendasPreferidas = emptyList(),
                        alertasActivadas = dto.usuario.avisosOfertas
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

    override suspend fun restablecerContrasenha(request: com.example.dealfinder_pc.modelo.RestablecerRequestDTO): Result<Unit> {
        return apiRepo.restablecerContrasenha(request)
    }

    override suspend fun cerrarSesion(): Result<Unit> {
        // Borramos el token de la memoria local y de SharedPreferences
        NetworkModule.authToken = null
        com.example.dealfinder_pc.TokenManager.clearToken()
        
        return apiRepo.cerrarSesion()
    }
}
