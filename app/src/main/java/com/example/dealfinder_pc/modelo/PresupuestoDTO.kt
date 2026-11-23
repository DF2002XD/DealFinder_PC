package com.example.dealfinder_pc.modelo

data class PresupuestoRespuestaDTO(
    val id: Long,
    val periodo: String, // "MENSUAL" o "ANUAL"
    val anio: Int,
    val mes: Int,
    val importe: Double,
    val gastado: Double,
    val saldo: Double,
    val repetir: Boolean,
    val heredado: Boolean
)

data class PresupuestoRequestDTO(
    val periodo: String, // "MENSUAL" o "ANUAL"
    val anio: Int,
    val mes: Int,
    val importe: Double,
    val repetir: Boolean
)
