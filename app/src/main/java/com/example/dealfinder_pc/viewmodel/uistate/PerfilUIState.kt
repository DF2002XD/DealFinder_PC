package com.example.dealfinder_pc.viewmodel.uistate

/**
 * Representa el estado de la interfaz de usuario para la pantalla del Perfil del usuario.
 *
 * @property nombreUsuario Nombre del usuario.
 * @property correoUsuario Correo del usuario.
 * @property presupuestoTexto Resumen del presupuesto mensual configurado.
 * @property monedaActual Moneda preferida del usuario.
 * @property tiendasTexto Resumen de cuántas tiendas tiene seleccionadas como favoritas.
 * @property avisosActivados Si tiene activadas las notificaciones push.
 * @property isLoading Indica si se están cargando los datos del usuario.
 */
data class PerfilUIState(
    val nombreUsuario: String = "",
    val correoUsuario: String = "",
    val presupuestoTexto: String = "No establecido",
    val monedaActual: String = "USD",
    val tiendasTexto: String = "0 seleccionadas",
    val avisosActivados: Boolean = false,
    val isLoading: Boolean = false
)
