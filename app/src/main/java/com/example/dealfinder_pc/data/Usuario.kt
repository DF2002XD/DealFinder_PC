package com.example.dealfinder_pc.data

/**
 * Modelo de dominio que representa al usuario actual.
 */
data class Usuario(
    val id: String,
    val nombreUsuario: String,
    val correo: String,
    val token: String,
    val presupuestoMensual: Double?,
    val monedaPreferida: String,
    val tiendasPreferidas: List<String>,
    val alertasActivadas: Boolean
)
