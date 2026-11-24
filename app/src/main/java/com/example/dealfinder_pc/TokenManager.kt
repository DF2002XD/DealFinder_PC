package com.example.dealfinder_pc

import android.content.Context
import android.content.SharedPreferences

/**
 * Objeto Singleton para gestionar el guardado persistente del Token JWT usando SharedPreferences.
 */
object TokenManager {
    private const val PREFS_NAME = "DealFinderPrefs"
    private const val KEY_TOKEN = "jwt_token"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Guarda el token de forma persistente.
     */
    fun saveToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    /**
     * Devuelve el token si existe, o null si no se ha guardado.
     */
    fun getToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    /**
     * Borra el token (útil para el logout o si expira).
     */
    fun clearToken() {
        prefs.edit().remove(KEY_TOKEN).apply()
    }
}
