package com.example.dealfinder_pc.data.repository

import com.example.dealfinder_pc.data.DealTienda
import com.example.dealfinder_pc.data.DetallesJuego
import com.example.dealfinder_pc.data.Oferta
import com.example.dealfinder_pc.data.PaginaOferta
import com.example.dealfinder_pc.data.retrofit.repo.IOfertasApiRepo
import com.example.dealfinder_pc.data.retrofit.repo.OfertasApiRepo

class OfertasRepository(
    private val apiRepo: IOfertasApiRepo = OfertasApiRepo()
) : IOfertasRepository {

    override suspend fun obtenerOfertas(query: String?, page: Int): Result<PaginaOferta> {
        val result = apiRepo.obtenerOfertas(query, page)
        return if (result.isSuccess) {
            val dto = result.getOrThrow()
            val ofertasDominio = dto.ofertas.map { ofertaDto ->
                Oferta(
                    id = ofertaDto.gameId,
                    titulo = ofertaDto.titulo,
                    precioRebajado = ofertaDto.precioOferta,
                    precioOriginal = ofertaDto.precioNormal,
                    descuentoPorcentaje = ofertaDto.descuento,
                    urlImagen = ofertaDto.thumbUrl,
                    nombreTienda = ofertaDto.tiendaNombre,
                    puntuacionMetacritic = ofertaDto.metacritic
                )
            }
            Result.success(PaginaOferta(ofertasDominio, dto.hayMas))
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
