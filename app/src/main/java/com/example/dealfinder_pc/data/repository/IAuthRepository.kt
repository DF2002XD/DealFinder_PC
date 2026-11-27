package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.Usuario
import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO
import com.example.dealfinder_pc.modelo.RestablecerRequestDTO

interface IAuthRepository {
    suspend fun iniciarSesion(request: LoginRequestDTO): Result<Usuario>
    suspend fun registrarUsuario(request: RegistroRequestDTO): Result<Usuario>
    suspend fun recuperarContrasenha(email: String): Result<Unit>
    suspend fun restablecerContrasenha(request: RestablecerRequestDTO): Result<Unit>
    suspend fun cerrarSesion(): Result<Unit>
}
