package com.example.dealfinder_pc.modelo

/**
 * DTO que devuelve el servidor Spring Boot tras un inicio de sesión o registro exitoso.
 */
data class UsuarioResponseDTO(
    val userId: String,
    val username: String,
    val email: String,
    val token: String,                  // JWT o token de sesión
    val monthlyBudget: Double?,         // Presupuesto mensual (si está configurado)
    val preferredCurrency: String,      // Ej: "USD", "EUR"
    val preferredStores: List<String>,  // IDs de las tiendas favoritas
    val dealsAlertsEnabled: Boolean
)
