package com.teresaferme.rickyandmortywhoiswho

import android.app.Application
import org.koin.core.context.startKoin

internal class RMApplication: Application() {
    override fun onCreate() {
        super.onCreate();
        this.initializeKoin()
    }

    private fun initializeKoin() {
        startKoin { modules(MainModule) }
    }
}
