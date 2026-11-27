package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.PaginaOferta
import kotlin.time.Duration.Companion.milliseconds

internal suspend fun cargarTodasLasPaginas(
    paginaInicial: Int,
    obtenerPagina: suspend (Int) -> Result<PaginaOferta>,
    alCargarPagina: (PaginaOferta) -> Unit
): Result<Unit> {
    if (paginaInicial !in 0..MAX_NUMERO_PAGINA) {
        return Result.failure(IllegalArgumentException("La página inicial está fuera del rango permitido"))
    }

    var pagina = paginaInicial

    while (pagina <= MAX_NUMERO_PAGINA) {
        val respuesta = obtenerPagina(pagina).getOrElse { return Result.failure(it) }
        if (respuesta.hayMas && respuesta.ofertas.isEmpty()) {
            return Result.failure(IllegalStateException("El servidor indicó que hay más páginas, pero no devolvió ofertas"))
        }

        alCargarPagina(respuesta)
        if (!respuesta.hayMas) {
            return Result.success(Unit)
        }
        if (pagina == MAX_NUMERO_PAGINA) {
            return Result.failure(IllegalStateException("Se alcanzó el límite de páginas de ofertas"))
        }
        
        // PAUSA de 1 segundo (1000ms) para no saturar la API y evitar el Error HTTP 503
        kotlinx.coroutines.delay(1000.milliseconds)
        
        pagina++
    }

    return Result.failure(IllegalStateException("Se alcanzó el límite de páginas de ofertas"))
}

private const val MAX_NUMERO_PAGINA = 10_000
