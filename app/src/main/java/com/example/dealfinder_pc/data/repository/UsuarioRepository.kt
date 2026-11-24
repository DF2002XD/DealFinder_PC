package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.Usuario
import com.example.dealfinder_pc.data.retrofit.repo.IUsuarioApiRepo
import com.example.dealfinder_pc.data.retrofit.repo.UsuarioApiRepo
import com.example.dealfinder_pc.modelo.ActualizarPerfilRequestDTO

class UsuarioRepository(
    private val apiRepo: IUsuarioApiRepo = UsuarioApiRepo()
) : IUsuarioRepository {

    override suspend fun obtenerPerfil(): Result<Usuario> {
        val result = apiRepo.obtenerPerfil()
        return if (result.isSuccess) {
            val dto = result.getOrNull()
            if (dto != null) {
                Result.success(
                    Usuario(
                        id = dto.id.toString(),
                        nombreUsuario = dto.nombreUsuario,
                        correo = dto.correo,
                        token = "", // Not provided by profile API anymore
                        presupuestoMensual = null,
                        monedaPreferida = dto.moneda,
                        tiendasPreferidas = dto.tiendasPreferidas ?: emptyList(),
                        alertasActivadas = dto.avisosOfertas
                    )
                )
            } else {
                Result.failure(Exception("Perfil no encontrado"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Error al cargar perfil"))
        }
    }

    override suspend fun actualizarPreferencias(request: ActualizarPerfilRequestDTO): Result<Unit> {
        return apiRepo.actualizarPreferencias(request)
    }

    override suspend fun cambiarCorreo(nuevoCorreo: String): Result<Unit> {
        return apiRepo.cambiarCorreo(nuevoCorreo)
    }

    override suspend fun cambiarContrasenha(actual: String, nueva: String): Result<Unit> {
        return apiRepo.cambiarContrasenha(actual, nueva)
    }
}
