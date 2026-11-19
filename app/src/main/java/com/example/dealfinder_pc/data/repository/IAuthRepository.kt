package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.Usuario
import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO

interface IAuthRepository {
    suspend fun iniciarSesion(request: LoginRequestDTO): Result<Usuario>
    suspend fun registrarUsuario(request: RegistroRequestDTO): Result<Usuario>
    suspend fun recuperarContrasenha(email: String): Result<Unit>
    suspend fun cerrarSesion(): Result<Unit>
}
