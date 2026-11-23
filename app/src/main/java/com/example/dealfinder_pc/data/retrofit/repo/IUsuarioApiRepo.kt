package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.ActualizarPerfilRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioRespuestaDTO

interface IUsuarioApiRepo {
    suspend fun obtenerPerfil(): Result<UsuarioRespuestaDTO>
    suspend fun actualizarPreferencias(request: ActualizarPerfilRequestDTO): Result<Unit>
    suspend fun cambiarCorreo(nuevoCorreo: String): Result<Unit>
    suspend fun cambiarContrasenha(actual: String, nueva: String): Result<Unit>
}
