@file:Suppress("unused", "ConstPropertyName", "PackageName")
package com.example.dealfinder_pc.vista.Navegacion

object Rutas {
    // Rutas sin menú inferior (Onboarding / Autenticación)
    const val Login = "login"
    const val Registro = "registro"
    const val RecuperarContrasenha = "recuperar_contrasenha"

    // Rutas con menú inferior (Main App)
    const val Ofertas = "ofertas"
    const val Deseos = "deseos"
    const val Presupuesto = "presupuesto"
    const val Perfil = "perfil"

    // Rutas de Detalles / Formularios internos
    const val DetallesOferta = "detalles_oferta/{idOferta}"
    const val Filtros = "filtros" // AÑADIDO
    const val Moneda = "moneda"
    const val Cuenta = "cuenta"
    const val TiendasFiltro = "tiendas_filtro"

    // Funciones auxiliares para rutas con parámetros
    fun detallesOferta(id: String) = "detalles_oferta/$id"
}