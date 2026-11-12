@file:Suppress("unused", "UNUSED_ANONYMOUS_PARAMETER", "PackageName")
package com.example.dealfinder_pc.vista.Navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dealfinder_pc.vista.Paginas.PaginaLogin
import com.example.dealfinder_pc.vista.Paginas.PaginaOfertas
import com.example.dealfinder_pc.vista.Paginas.PaginaRecuContra
import com.example.dealfinder_pc.vista.Paginas.PaginaRegistro
import com.example.dealfinder_pc.vista.misComponentes.BottomAppBarNav
import com.example.dealfinder_pc.vista.Navegacion.Rutas.Login
import com.example.dealfinder_pc.vista.Navegacion.Rutas.Registro
import com.example.dealfinder_pc.vista.Navegacion.Rutas.RecuperarContrasenha
import com.example.dealfinder_pc.vista.Navegacion.Rutas.Ofertas
import com.example.dealfinder_pc.vista.Navegacion.Rutas.Deseos
import com.example.dealfinder_pc.vista.Navegacion.Rutas.Presupuesto
import com.example.dealfinder_pc.vista.Navegacion.Rutas.Perfil

// Helpers extra para que devuelva algo rápido por ahora si no existen las pantallas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.example.dealfinder_pc.ui.theme.displayFontFamily

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Observamos en qué ruta estamos en cada momento
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Definimos qué pestañas activan el menú inferior
    val rutasConMenuInferior = listOf(Ofertas, Deseos, Presupuesto, Perfil)
    val mostrarBottomBar = currentRoute in rutasConMenuInferior

    // Calculamos qué pestaña (0, 1, 2, 3) está activa para pasársela al componente
    val indicePestanha = when (currentRoute) {
        Ofertas -> 0
        Deseos -> 1
        Presupuesto -> 2
        Perfil -> 3
        else -> 0
    }

    Scaffold(
        containerColor = Color(0xFF191636), // El fondo global
        bottomBar = {
            if (mostrarBottomBar) {
                BottomAppBarNav(
                    pestanhaSeleccionada = indicePestanha,
                    onPestanhaClick = { index ->
                        // Navegamos según la pestaña pulsada
                        val rutaDestino = when (index) {
                            0 -> Ofertas
                            1 -> Deseos
                            2 -> Presupuesto
                            3 -> Perfil
                            else -> Ofertas
                        }
                        navController.navigate(rutaDestino) {
                            // popUpTo evita que se apilen 1000 pantallas al navegar por el menú
                            popUpTo(Ofertas) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Login, // Empezamos en la pantalla de Login
            modifier = Modifier.padding(innerPadding)
        ) {

            // --- BLOQUE DE AUTENTICACIÓN ---

            composable(Login) {
                PaginaLogin(
                    onNavigateToRegistro = { navController.navigate(Registro) },
                    onNavigateToRecuperarContrasenha = { navController.navigate(RecuperarContrasenha) },
                    onLoginSuccess = {
                        // Al loguearse bien, vamos a Ofertas y destruimos el historial de Login
                        navController.navigate(Ofertas) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            composable(Registro) {
                PaginaRegistro(
                    onNavigateBack = { navController.popBackStack() }, // Vuelve al Login
                    onRegisterSuccess = {
                        navController.navigate(Ofertas) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            composable(RecuperarContrasenha) {
                PaginaRecuContra(
                    onNavigateBack = { navController.popBackStack() },
                    onEmailSent = { navController.popBackStack() } // Simula vuelta al login al enviar
                )
            }

            // --- BLOQUE PRINCIPAL (MENÚ INFERIOR) ---

            composable(Ofertas) {
                PaginaOfertas(
                    onNavigateToFiltros = { /* Próximamente -> navController.navigate(Filtros) */ },
                    onNavigateToDetalles = { idJuego ->
                        // Próximamente -> navController.navigate(Rutas.detallesOferta(idJuego))
                    }
                )
            }

            composable(Deseos) {
                // Como PaginaDeseos aún no está ensamblada, ponemos un placeholder chulo
                PlaceholderPantalla("Pantalla Deseos (Próximamente)")
            }

            composable(Presupuesto) {
                PlaceholderPantalla("Pantalla Presupuesto (Próximamente)")
            }

            composable(Perfil) {
                PlaceholderPantalla("Pantalla Perfil (Próximamente)")
            }
        }
    }
}

// Pantalla temporal para las rutas del menú que aún no estan hechas "Páginas"
@Composable
fun PlaceholderPantalla(texto: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = texto, color = Color.White, fontFamily = displayFontFamily)
    }
}