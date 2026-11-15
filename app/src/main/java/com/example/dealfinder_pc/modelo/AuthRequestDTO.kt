package com.example.dealfinder_pc.modelo

/**
 * DTO para enviar las credenciales de inicio de sesión al servidor Spring Boot.
 */
data class LoginRequestDTO(
    val email: String,
    val passwordHash: String // Es buena práctica enviar la clave hasheada o usar HTTPS siempre
)

/**
 * DTO para enviar los datos de registro de un usuario nuevo al servidor Spring Boot.
 */
data class RegistroRequestDTO(
    val username: String,
    val email: String,
    val passwordHash: String
)
