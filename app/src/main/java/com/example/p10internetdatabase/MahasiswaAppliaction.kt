package com.example.p10internetdatabase

import android.app.Application
import com.example.p10internetdatabase.repository.AppContainer
import com.example.p10internetdatabase.repository.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}