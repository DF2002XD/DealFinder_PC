package com.example.dealfinder_pc.modelo

data class UsuarioRespuestaDTO(
    val id: Long,
    val nombreUsuario: String,
    val correo: String,
    val moneda: String,
    val avisosOfertas: Boolean,
    val tiendasPreferidas: List<String>? = null
)

data class ActualizarPerfilRequestDTO(
    val correo: String?,
    val moneda: String?,
    val avisosOfertas: Boolean?,
    val tiendasPreferidas: List<String>? = null
)

data class CambiarContrasenaRequestDTO(
    val antiguaContrasena: String,
    val nuevaContrasena: String
)

data class TiendasRequestDTO(
    val tiendaIds: List<String>
)
