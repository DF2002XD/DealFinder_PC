package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioResponseDTO

interface IAuthApiRepo {
    suspend fun iniciarSesion(request: LoginRequestDTO): Result<UsuarioResponseDTO>
    suspend fun registrarUsuario(request: RegistroRequestDTO): Result<UsuarioResponseDTO>
    suspend fun recuperarContrasenha(email: String): Result<Unit>
    suspend fun cerrarSesion(): Result<Unit>
}
