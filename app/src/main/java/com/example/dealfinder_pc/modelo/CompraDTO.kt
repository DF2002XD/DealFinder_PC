package com.example.dealfinder_pc.modelo

data class CompraRespuestaDTO(
    val id: Long,
    val gameId: String,
    val titulo: String,
    val tiendaId: String,
    val precio: Double,
    val moneda: String,
    val fecha: String // Viene como cadena ISO (LocalDateTime)
)

data class CompraRequestDTO(
    val gameId: String,
    val titulo: String,
    val tiendaId: String,
    val precio: Double,
    val moneda: String,
    val fecha: String // "2024-05-15T12:00:00"
)

data class CompraResultadoDTO(
    val compra: CompraRespuestaDTO,
    val presupuestoActualizado: PresupuestoRespuestaDTO?
)

data class ResumenGastoDTO(
    val anio: Int,
    val gastoTotal: Double,
    val meses: List<GastoMesDTO>
)

data class GastoMesDTO(
    val mes: Int,
    val gasto: Double
)
