package com.example.dealfinder_pc.data.retrofit.network

import com.example.dealfinder_pc.modelo.ActualizarPerfilRequestDTO
import com.example.dealfinder_pc.modelo.AuthRespuestaDTO
import com.example.dealfinder_pc.modelo.CambiarContrasenaRequestDTO
import com.example.dealfinder_pc.modelo.DeseoRequestDTO
import com.example.dealfinder_pc.modelo.DeseoRespuestaDTO
import com.example.dealfinder_pc.modelo.JuegoRespuestaDTO
import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.modelo.OfertaRespuestaDTO
import com.example.dealfinder_pc.modelo.RegistroRequestDTO
import com.example.dealfinder_pc.modelo.UsuarioRespuestaDTO
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
    suspend fun login(@Body request: LoginRequestDTO): Response<AuthRespuestaDTO>

    @POST("api/auth/register")
    suspend fun register(@Body request: RegistroRequestDTO): Response<AuthRespuestaDTO>

    @POST("api/auth/recover")
    suspend fun recoverPassword(@Query("email") email: String): Response<Unit>
    
    @POST("api/auth/logout")
    suspend fun logout(): Response<Unit>

    // --- USUARIO ---
    @GET("api/user/profile")
    suspend fun getUserProfile(): Response<UsuarioRespuestaDTO>

    @PUT("api/user/preferences")
    suspend fun updatePreferences(@Body request: ActualizarPerfilRequestDTO): Response<Unit>

    @PUT("api/user/email")
    suspend fun updateEmail(@Query("newEmail") email: String): Response<Unit>

    @PUT("api/user/password")
    suspend fun updatePassword(@Body request: CambiarContrasenaRequestDTO): Response<Unit>

    // --- OFERTAS (BFF que saca los datos de CheapShark) ---
    @GET("api/deals")
    suspend fun getOfertas(
        @Query("query") query: String? = null,
        @Query("maxPrice") maxPrice: Double? = null,
        @Query("onSale") onSale: Boolean? = null
    ): Response<List<OfertaRespuestaDTO>>

    @GET("api/deals/{id}")
    suspend fun getDetallesJuego(@Path("id") gameId: String): Response<JuegoRespuestaDTO>

    // --- LISTA DE DESEOS ---
    @GET("api/wishlist")
    suspend fun getWishlist(): Response<List<DeseoRespuestaDTO>>

    @POST("api/wishlist/toggle")
    suspend fun toggleWishlistItem(@Body request: DeseoRequestDTO): Response<Unit>

    @POST("api/wishlist/{id}/purchase")
    suspend fun markAsPurchased(@Path("id") gameId: String): Response<Unit>
}
