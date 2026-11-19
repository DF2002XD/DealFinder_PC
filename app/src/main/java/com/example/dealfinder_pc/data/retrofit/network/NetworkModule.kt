package com.example.dealfinder_pc.data.retrofit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto Singleton que configura la instancia de Retrofit para conectarse al servidor Spring Boot.
 */
object NetworkModule {

    // Cambia esto a la IP de tu servidor real cuando lo despliegues.
    // 10.0.2.2 es la IP especial para conectarse a localhost desde el emulador de Android.
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: DealFinderApiService by lazy {
        retrofit.create(DealFinderApiService::class.java)
    }
}
