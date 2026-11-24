package com.example.dealfinder_pc

import android.app.Application

class DealFinderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        TokenManager.init(this)
    }
}
