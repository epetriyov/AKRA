package com.akra.example

import android.app.Application

/**
 * Created by Евгений on 8/21/2017.
 */
class App : Application() {


    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

}
