package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.AuthRespuestaDTO
import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO

import com.example.dealfinder_pc.modelo.RestablecerRequestDTO

interface IAuthApiRepo {
    suspend fun iniciarSesion(request: LoginRequestDTO): Result<AuthRespuestaDTO>
    suspend fun registrarUsuario(request: RegistroRequestDTO): Result<AuthRespuestaDTO>
    suspend fun recuperarContrasenha(email: String): Result<Unit>
    suspend fun restablecerContrasenha(request: RestablecerRequestDTO): Result<Unit>
    suspend fun cerrarSesion(): Result<Unit>
}
