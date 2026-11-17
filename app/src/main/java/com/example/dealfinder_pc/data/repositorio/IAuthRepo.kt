package com.example.dealfinder_pc.data.repositorio

import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioResponseDTO

/**
 * Interfaz para el repositorio de autenticación.
 */
interface IAuthRepo {
    suspend fun iniciarSesion(request: LoginRequestDTO): Result<UsuarioResponseDTO>
    suspend fun registrarUsuario(request: RegistroRequestDTO): Result<UsuarioResponseDTO>
    suspend fun recuperarContrasenha(email: String): Result<Unit>
    suspend fun cerrarSesion(): Result<Unit>
}
