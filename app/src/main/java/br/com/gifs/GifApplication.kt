package br.com.gifs

import android.app.Application
import br.com.gifs.di.gifModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GifApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GifApplication)
            modules(gifModule)
        }
    }

}