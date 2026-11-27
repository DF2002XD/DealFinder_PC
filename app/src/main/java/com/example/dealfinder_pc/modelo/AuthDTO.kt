package com.example.dealfinder_pc.modelo

data class RegistroRequestDTO(
    val nombreUsuario: String,
    val correo: String,
    val contrasena: String
)

data class LoginRequestDTO(
    val correo: String,
    val contrasena: String,
    val mantenerSesion: Boolean
)

data class TokenRequestDTO(
    val refreshToken: String
)

data class RecuperarRequestDTO(
    val correo: String
)

data class RestablecerRequestDTO(
    val correo: String,
    val token: String,
    val nuevaContrasena: String
)

data class AuthRespuestaDTO(
    val accessToken: String,
    val refreshToken: String,
    val expiraEnSegundos: Long,
    val usuario: UsuarioRespuestaDTO
)
