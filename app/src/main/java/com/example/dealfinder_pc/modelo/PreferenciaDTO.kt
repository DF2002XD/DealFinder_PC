package com.example.dealfinder_pc.modelo

/**
 * DTO para enviar una petición de actualización de preferencias de usuario al servidor Spring Boot.
 * Permite actualizar los parámetros del usuario sin enviar toda su información.
 */
data class PreferenciaUpdateRequestDTO(
    val userId: String,
    val preferredCurrency: String? = null,
    val preferredStores: List<String>? = null,
    val dealsAlertsEnabled: Boolean? = null
)
