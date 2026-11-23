package com.example.dealfinder_pc.data.retrofit.repo

import com.example.dealfinder_pc.modelo.PreferenciaUpdateRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioResponseDTO

interface IUsuarioApiRepo {
    suspend fun obtenerPerfil(): Result<UsuarioResponseDTO>
    suspend fun actualizarPreferencias(request: PreferenciaUpdateRequestDTO): Result<Unit>
    suspend fun cambiarCorreo(nuevoCorreo: String): Result<Unit>
    suspend fun cambiarContrasenha(actual: String, nueva: String): Result<Unit>
}
