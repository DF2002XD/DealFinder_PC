@file:Suppress("unused", "UNUSED_ANONYMOUS_PARAMETER", "PackageName")
package com.example.dealfinder_pc.vista.navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import com.example.dealfinder_pc.viewmodel.vm.OfertasViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dealfinder_pc.vista.paginas.PaginaDeseos
import com.example.dealfinder_pc.vista.paginas.PaginaFiltro
import com.example.dealfinder_pc.vista.paginas.PaginaLogin
import com.example.dealfinder_pc.vista.paginas.PaginaCuenta
import com.example.dealfinder_pc.vista.paginas.PaginaDetalles
import com.example.dealfinder_pc.vista.paginas.PaginaMoneda
import com.example.dealfinder_pc.vista.paginas.PaginaOfertas
import com.example.dealfinder_pc.vista.paginas.PaginaPerfil
import com.example.dealfinder_pc.vista.paginas.PaginaRegistro
import com.example.dealfinder_pc.vista.paginas.PaginaRestablecerContra
import com.example.dealfinder_pc.vista.navegacion.Rutas.RestablecerContrasenha
import com.example.dealfinder_pc.vista.paginas.PaginaTienda
import com.example.dealfinder_pc.vista.paginas.PaginaRecuContra
import com.example.dealfinder_pc.vista.misComponentes.BottomAppBarNav
import com.example.dealfinder_pc.vista.navegacion.Rutas.Login
import com.example.dealfinder_pc.vista.navegacion.Rutas.Registro
import com.example.dealfinder_pc.vista.navegacion.Rutas.RecuperarContrasenha
import com.example.dealfinder_pc.vista.navegacion.Rutas.Ofertas
import com.example.dealfinder_pc.vista.navegacion.Rutas.Filtros
import com.example.dealfinder_pc.vista.navegacion.Rutas.Deseos
import com.example.dealfinder_pc.vista.navegacion.Rutas.Presupuesto
import com.example.dealfinder_pc.vista.navegacion.Rutas.Perfil
import com.example.dealfinder_pc.vista.navegacion.Rutas.Cuenta
import com.example.dealfinder_pc.vista.navegacion.Rutas.TiendasFiltro
import com.example.dealfinder_pc.vista.navegacion.Rutas.Moneda
import com.example.dealfinder_pc.vista.navegacion.Rutas.DetallesOferta

// Helpers extra para que devuelva algo rápido por ahora si no existen las pantallas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.example.dealfinder_pc.ui.theme.displayFontFamily
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dealfinder_pc.viewmodel.vm.AppNavigationViewModel
import com.example.dealfinder_pc.vista.misComponentes.TopAppBarNav

@Composable
fun AppNavigation(
    appViewModel: AppNavigationViewModel = viewModel()
) {
    val navController = rememberNavController()
    
    // Conectamos el estado de navegación REAL del ViewModel
    val uiState by appViewModel.uiState.collectAsState()

    // Si aún está comprobando si el usuario tiene sesión guardada, podríamos mostrar un Splash
    if (uiState.isCheckUserSession) {
        // En el futuro: Splash Screen o loader aquí
        return
    }

    // Observamos en qué ruta estamos en cada momento
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Definimos qué pestañas activan el menú inferior
    val rutasConBottomBar = listOf(Ofertas, Deseos, Presupuesto, Perfil)
    val mostrarBottomBar = currentRoute in rutasConBottomBar

    val rutasConTopBar = listOf(Cuenta, Moneda, TiendasFiltro, Filtros, DetallesOferta)
    val mostrarTopBar = currentRoute in rutasConTopBar

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
        topBar = {
            if (mostrarTopBar) {
                TopAppBarNav(onBackClick = { navController.popBackStack() })
            }
        },
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
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
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
            startDestination = uiState.initialRoute, // Toma la ruta inicial del UIState
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
                    onNavigateBack = { navController.popBackStack() }, // Vuelve al Login al darle a "Ya tengo cuenta"
                    onRegisterSuccess = {
                        // Al registrarse con éxito, volvemos a la pantalla de Login
                        // y opcionalmente podrías mostrar un Toast desde el ViewModel
                        navController.popBackStack()
                    }
                )
            }

            composable(RecuperarContrasenha) {
                // Instanciamos el viewModel a nivel de ruta para sacar el email y pasarlo
                val recuperarVm: com.example.dealfinder_pc.viewmodel.vm.RecuperarContraViewModel = viewModel()
                val recuperarState by recuperarVm.uiState.collectAsState()
                
                PaginaRecuContra(
                    onNavigateBack = { navController.popBackStack() },
                    onEmailSent = { 
                        navController.navigate(Rutas.restablecerContrasenha(recuperarState.correo)) {
                            popUpTo(RecuperarContrasenha) { inclusive = true }
                        }
                    },
                    viewModel = recuperarVm
                )
            }

            composable(RestablecerContrasenha) { backStackEntry ->
                val correoEncoded = backStackEntry.arguments?.getString("correo") ?: ""
                val correoDecoded = android.net.Uri.decode(correoEncoded)
                
                PaginaRestablecerContra(
                    correoAsociado = correoDecoded,
                    onNavigateToLogin = { 
                        navController.navigate(Login) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            // --- BLOQUE PRINCIPAL (MENÚ INFERIOR) ---

            composable(Ofertas) { backStackEntry ->
                val savedStateHandle = backStackEntry.savedStateHandle
                
                PaginaOfertas(
                    onNavigateToFiltros = { navController.navigate(Filtros) },
                    onNavigateToDetalles = { idJuego ->
                        navController.navigate(Rutas.detallesOferta(idJuego))
                    },
                    savedStateHandle = savedStateHandle
                )
            }

            composable(Filtros) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Ofertas)
                }
                val ofertasViewModel: OfertasViewModel = viewModel(parentEntry)
                val ofertasUiState by ofertasViewModel.uiState.collectAsState()
                
                PaginaFiltro(
                    onNavigateBack = { precioMaximo, soloGratis, soloEnOferta, ordenSeleccionado, tiendasSeleccionadas -> 
                        navController.previousBackStackEntry?.savedStateHandle?.set("precioMaximo", precioMaximo)
                        navController.previousBackStackEntry?.savedStateHandle?.set("soloGratis", soloGratis)
                        navController.previousBackStackEntry?.savedStateHandle?.set("soloEnOferta", soloEnOferta)
                        navController.previousBackStackEntry?.savedStateHandle?.set("ordenSeleccionado", ordenSeleccionado)
                        navController.previousBackStackEntry?.savedStateHandle?.set("tiendasSeleccionadas", tiendasSeleccionadas)
                        navController.popBackStack()
                    },
                    ofertasUiState = ofertasUiState
                )
            }

            composable(Deseos) {
                PaginaDeseos(
                    onDeseoClick = { idJuego ->
                        navController.navigate(Rutas.detallesOferta(idJuego))
                    }
                )
            }

            composable(DetallesOferta) { backStackEntry ->
                val idOferta = backStackEntry.arguments?.getString("idOferta") ?: ""
                val contexto = androidx.compose.ui.platform.LocalContext.current
                PaginaDetalles(
                    idOferta = idOferta,
                    onVolver = { navController.popBackStack() }, // ESTO ERA LO QUE NO ESTABA BIEN PASADO
                    onTiendaClick = { ofertaTienda -> 
                        // Abrir enlace en el navegador
                        if (!ofertaTienda.urlEnlace.isNullOrBlank()) {
                            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(ofertaTienda.urlEnlace))
                            contexto.startActivity(intent)
                        } else {
                            android.widget.Toast.makeText(contexto, "Enlace no disponible", android.widget.Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

            composable(Presupuesto) {
                PlaceholderPantalla("Pantalla Presupuesto (Próximamente)")
            }

            composable(Perfil) {
                PaginaPerfil(
                    onNavigateToCuenta = { navController.navigate(Cuenta) },
                    onNavigateToPresupuesto = { navController.navigate(Presupuesto) },
                    onNavigateToMoneda = { navController.navigate(Moneda) },
                    onNavigateToTiendas = { navController.navigate(TiendasFiltro) },
                    onCerrarSesionClick = {
                        // Navegamos al Login y borramos el historial para que no pueda darle a "Atrás"
                        navController.navigate(Login) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
            
            // --- SUBPANTALLAS DEL PERFIL ---
            
            composable(Cuenta) {
                PaginaCuenta(
                    onRecuperarContrasenhaClick = { navController.navigate("recuperar_contrasena_desde_cuenta") }
                )
            }

            composable("recuperar_contrasena_desde_cuenta") {
                val recuperarVm: com.example.dealfinder_pc.viewmodel.vm.RecuperarContraViewModel = viewModel()
                val recuperarState by recuperarVm.uiState.collectAsState()
                
                PaginaRecuContra(
                    onNavigateBack = { navController.popBackStack() },
                    onEmailSent = { 
                        navController.navigate(Rutas.restablecerContrasenha(recuperarState.correo)) {
                            popUpTo("recuperar_contrasena_desde_cuenta") { inclusive = true }
                        }
                    }, 
                    desdeCuenta = true,
                    viewModel = recuperarVm
                )
            }
            composable(Moneda) {
                PaginaMoneda()
            }
            composable(TiendasFiltro) {
                PaginaTienda()
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