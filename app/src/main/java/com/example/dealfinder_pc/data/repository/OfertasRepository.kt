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
                    id = dto.gameID,
                    titulo = dto.title,
                    precioRebajado = dto.salePrice,
                    precioOriginal = dto.normalPrice,
                    descuentoPorcentaje = dto.savings,
                    urlImagen = dto.thumbUrl,
                    nombreTienda = dto.storeID,
                    puntuacionMetacritic = dto.metacriticScore
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
                    id = dto.gameID,
                    titulo = dto.title,
                    precioRebajado = dto.salePrice,
                    precioOriginal = dto.normalPrice,
                    descuentoPorcentaje = dto.savings,
                    urlImagen = dto.thumbUrl,
                    nombreTienda = dto.storeID,
                    puntuacionMetacritic = dto.metacriticScore
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
                    id = dto.gameID,
                    titulo = dto.title,
                    urlImagen = dto.thumbUrl,
                    minimoHistoricoPrecio = dto.historicalLowPrice,
                    minimoHistoricoFecha = dto.historicalLowDate,
                    estaEnDeseos = dto.isOnWishlist,
                    ofertasTiendas = dto.deals.map { dealDto ->
                        DealTienda(
                            idOferta = dealDto.dealID,
                            idTienda = dealDto.storeID,
                            nombreTienda = dealDto.storeName,
                            precioActual = dealDto.price,
                            precioNormal = dealDto.retailPrice,
                            descuentoPorcentaje = dealDto.savings
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
