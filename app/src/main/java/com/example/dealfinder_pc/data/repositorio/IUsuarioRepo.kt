package com.example.dealfinder_pc.data.repositorio

import com.example.dealfinder_pc.modelo.PreferenciaUpdateRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioResponseDTO

/**
 * Interfaz para el repositorio de gestión de la cuenta y preferencias del usuario.
 */
interface IUsuarioRepo {
    suspend fun obtenerPerfil(): Result<UsuarioResponseDTO>
    suspend fun actualizarPreferencias(request: PreferenciaUpdateRequestDTO): Result<Unit>
    suspend fun cambiarCorreo(nuevoCorreo: String): Result<Unit>
    suspend fun cambiarContrasenha(actual: String, nueva: String): Result<Unit>
}
