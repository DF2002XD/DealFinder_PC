package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.Usuario
import com.example.dealfinder_pc.modelo.ActualizarPerfilRequestDTO

interface IUsuarioRepository {
    suspend fun obtenerPerfil(): Result<Usuario>
    suspend fun actualizarPreferencias(request: ActualizarPerfilRequestDTO): Result<Unit>
    suspend fun cambiarCorreo(nuevoCorreo: String): Result<Unit>
    suspend fun cambiarContrasenha(actual: String, nueva: String): Result<Unit>
}
