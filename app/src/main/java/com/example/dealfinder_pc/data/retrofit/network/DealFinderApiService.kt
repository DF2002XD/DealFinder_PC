package com.example.dealfinder_pc.data.retrofit.network

import com.example.dealfinder_pc.modelo.AccionDeseoRequestDTO
import com.example.dealfinder_pc.modelo.DeseoDTO
import com.example.dealfinder_pc.modelo.DetallesJuegoDTO
import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.OfertaDTO
import com.example.dealfinder_pc.modelo.PreferenciaUpdateRequestDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz que define todos los Endpoints de tu servidor Spring Boot.
 */
interface DealFinderApiService {

    // --- AUTH ---
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequestDTO): Response<UsuarioResponseDTO>

    @POST("api/auth/register")
    suspend fun register(@Body request: RegistroRequestDTO): Response<UsuarioResponseDTO>

    @POST("api/auth/recover")
    suspend fun recoverPassword(@Query("email") email: String): Response<Unit>

    // --- USUARIO ---
    @GET("api/user/profile")
    suspend fun getUserProfile(): Response<UsuarioResponseDTO>

    @PUT("api/user/preferences")
    suspend fun updatePreferences(@Body request: PreferenciaUpdateRequestDTO): Response<Unit>

    // --- OFERTAS (BFF que saca los datos de CheapShark) ---
    @GET("api/deals")
    suspend fun getOfertas(
        @Query("query") query: String? = null,
        @Query("maxPrice") maxPrice: Double? = null,
        @Query("onSale") onSale: Boolean? = null
    ): Response<List<OfertaDTO>>

    @GET("api/deals/{id}")
    suspend fun getDetallesJuego(@Path("id") gameId: String): Response<DetallesJuegoDTO>

    // --- LISTA DE DESEOS ---
    @GET("api/wishlist")
    suspend fun getWishlist(): Response<List<DeseoDTO>>

    @POST("api/wishlist/toggle")
    suspend fun toggleWishlistItem(@Body request: AccionDeseoRequestDTO): Response<Unit>
}
