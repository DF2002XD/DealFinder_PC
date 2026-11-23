package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.DealTienda
import com.example.dealfinder_pc.data.DetallesJuego
import com.example.dealfinder_pc.data.Oferta
import com.example.dealfinder_pc.data.retrofit.repo.IOfertasApiRepo
import com.example.dealfinder_pc.data.retrofit.repo.OfertasApiRepo

class OfertasRepository(
    private val apiRepo: IOfertasApiRepo = OfertasApiRepo()
) : IOfertasRepository {

    override suspend fun obtenerOfertas(): Result<List<Oferta>> {
        val result = apiRepo.obtenerOfertas()
        return if (result.isSuccess) {
            val dtos = result.getOrNull() ?: emptyList()
            val dominio = dtos.map { dto ->
                Oferta(
                    id = dto.gameId,
                    titulo = dto.titulo,
                    precioRebajado = dto.precioOferta,
                    precioOriginal = dto.precioNormal,
                    descuentoPorcentaje = dto.descuento,
                    urlImagen = dto.thumbUrl,
                    nombreTienda = dto.tiendaNombre,
                    puntuacionMetacritic = dto.metacritic
                )
            }
            Result.success(dominio)
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Error desconocido"))
        }
    }

    override suspend fun buscarOfertas(query: String): Result<List<Oferta>> {
        val result = apiRepo.buscarOfertas(query)
        return if (result.isSuccess) {
            val dtos = result.getOrNull() ?: emptyList()
            val dominio = dtos.map { dto ->
                Oferta(
                    id = dto.gameId,
                    titulo = dto.titulo,
                    precioRebajado = dto.precioOferta,
                    precioOriginal = dto.precioNormal,
                    descuentoPorcentaje = dto.descuento,
                    urlImagen = dto.thumbUrl,
                    nombreTienda = dto.tiendaNombre,
                    puntuacionMetacritic = dto.metacritic
                )
            }
            Result.success(dominio)
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Error desconocido"))
        }
    }

    override suspend fun obtenerDetallesJuego(gameID: String): Result<DetallesJuego> {
        val result = apiRepo.obtenerDetallesJuego(gameID)
        return if (result.isSuccess) {
            val dto = result.getOrNull()
            if (dto != null) {
                val dominio = DetallesJuego(
                    id = dto.id,
                    titulo = dto.titulo,
                    urlImagen = dto.thumbUrl,
                    minimoHistoricoPrecio = dto.precioHistoricoMinimo,
                    minimoHistoricoFecha = dto.fechaHistoricoMinimo,
                    estaEnDeseos = dto.enDeseos,
                    ofertasTiendas = dto.ofertas.map { dealDto ->
                        DealTienda(
                            idOferta = dealDto.dealId,
                            idTienda = dealDto.tiendaId,
                            nombreTienda = dealDto.tiendaNombre,
                            precioActual = dealDto.precioOferta,
                            precioNormal = dealDto.precioNormal,
                            descuentoPorcentaje = dealDto.descuento
                        )
                    }
                )
                Result.success(dominio)
            } else {
                Result.failure(Exception("Juego no encontrado"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Error desconocido"))
        }
    }
}
