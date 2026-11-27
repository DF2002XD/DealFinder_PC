package com.example.dealfinder_pc.viewmodel.uistate

data class RestablecerContraUIState(
    val codigo: String = "",
    val nuevaContrasena: String = "",
    val repetirContrasena: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)
