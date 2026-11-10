package com.example.dealfinder_pc.viewmodel.uistate

data class PerfilUIState(
    val nombreUsuario: String,
    val correoUsuario: String,
    val presupuestoTexto: String,
    val monedaActual: String,
    val tiendasTexto: String,
    val avisosActivados: Boolean
)
