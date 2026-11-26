package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IDeseosRepository
import com.example.dealfinder_pc.data.repository.DeseosRepository
import com.example.dealfinder_pc.viewmodel.uistate.DeseoUIState
import com.example.dealfinder_pc.viewmodel.uistate.DeseosUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class DeseosViewModel(
    private val repository: IDeseosRepository = DeseosRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(DeseosUIState())
    val uiState: StateFlow<DeseosUIState> = _uiState.asStateFlow()

    init {
        cargarDeseos()
    }
    
    fun recargarDatos() {
        cargarDeseos()
    }

    private fun cargarDeseos() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = repository.obtenerListaDeseos()
            
            if (result.isSuccess) {
                val dtoList = result.getOrDefault(emptyList())
                
                // Mapeamos Modelo de Dominio -> UIState
                val lista = dtoList.map { deseo ->
                    DeseoUIState(
                        id = deseo.id, 
                        titulo = deseo.titulo, 
                        precioRebajado = "$${deseo.mejorPrecioActual}", 
                        urlImagen = deseo.urlImagen
                    )
                }

                // Calculamos la nueva suma total de los precios
                val sumaTotal = lista.sumOf { deseo ->
                    deseo.precioRebajado.replace("$", "").toDoubleOrNull() ?: 0.0
                }
                
                // Formateamos la suma a dos decimales
                val sumaTotalFormateada = String.format(Locale.US, "%.2f", sumaTotal)

                _uiState.update {
                    it.copy(
                        juegosEnDeseos = lista,
                        precioTotalEstimado = "${lista.size} juegos · aprox. $$sumaTotalFormateada",
                        isLoading = false
                    )
                }
            } else {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Error al cargar deseos") }
            }
        }
    }

    fun marcarComoComprado(idJuego: String) {
        // En la realidad, esto actualizaría el presupuesto mensual y borraría o marcaría el juego en DB.
        eliminarJuego(idJuego)
    }

    fun eliminarJuego(idJuego: String) {
        val listaActualizada = _uiState.value.juegosEnDeseos.filter { it.id != idJuego }
        val juegoABorrar = _uiState.value.juegosEnDeseos.find { it.id == idJuego }
        
        // Calculamos la nueva suma total de los precios
        val sumaTotal = listaActualizada.sumOf { deseo ->
            // Quitamos el símbolo '$' para poder sumar los números
            deseo.precioRebajado.replace("$", "").toDoubleOrNull() ?: 0.0
        }
        
        // Formateamos la suma a dos decimales
        val sumaTotalFormateada = String.format(Locale.US, "%.2f", sumaTotal)

        // 1. Borramos el juego visualmente para que el usuario tenga respuesta inmediata
        _uiState.update {
            it.copy(
                juegosEnDeseos = listaActualizada,
                precioTotalEstimado = "${listaActualizada.size} juegos · aprox. $$sumaTotalFormateada"
            )
        }
        
        // 2. Comunicamos el borrado al servidor de Spring Boot usando toggleDeseos
        if (juegoABorrar != null) {
            viewModelScope.launch {
                val request = com.example.dealfinder_pc.modelo.DeseoRequestDTO(
                    gameId = juegoABorrar.id,
                    titulo = juegoABorrar.titulo,
                    thumbUrl = juegoABorrar.urlImagen,
                    precioReferencia = juegoABorrar.precioRebajado.replace("$", "").toDoubleOrNull() ?: 0.0
                )
                val result = repository.modificarDeseo(request)
                
                // Opcional: si fallara la API, aquí podríamos deshacer el borrado visual y volver a añadir el juego a la lista
                if (result.isFailure) {
                    recargarDatos()
                }
            }
        }
    }
}
