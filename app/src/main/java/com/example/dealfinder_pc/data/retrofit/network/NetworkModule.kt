package com.example.dealfinder_pc.data.retrofit.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto Singleton que configura la instancia de Retrofit para conectarse al servidor Spring Boot.
 */
object NetworkModule {

    // Variable en memoria para almacenar el token.
    var authToken: String? = null

    // Cambia esto a la IP de tu servidor real cuando lo despliegues.
    private const val BASE_URL = "http://10.0.2.2:8080/"

    // Interceptor que inyecta el token en la cabecera Authorization de cada petición HTTP
    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        
        // Buscamos el token en RAM. Si no está en RAM (ej: al abrir la app de nuevo),
        // buscamos si hay uno guardado en SharedPreferences (disco duro).
        val token = authToken ?: com.example.dealfinder_pc.TokenManager.getToken()
        
        // Si sigue sin haber token (ni en RAM ni en Disco), no añadimos cabecera de seguridad
        if (token.isNullOrBlank()) {
            return@Interceptor chain.proceed(originalRequest)
        }

        // Si hay token válido, lo adjuntamos como Bearer Token
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        
        chain.proceed(newRequest)
    }

    // Interceptor de logs para ver EXACTAMENTE qué enviamos y qué recibimos
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Cliente OkHttp personalizado con los interceptores
    private val okHttpClient = OkHttpClient.Builder()
        // El authInterceptor DEBE ir primero para que inyecte el token en la petición original
        .addInterceptor(authInterceptor)    
        // El logger va después, así nos imprime la petición ya modificada (con el Bearer token visible)
        .addInterceptor(loggingInterceptor) 
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: DealFinderApiService by lazy {
        retrofit.create(DealFinderApiService::class.java)
    }
}
