package com.akra.example

import android.app.Application

/**
 * Created by Евгений on 8/21/2017.
 */
class AKRAApplication : Application() {

    lateinit var instance: AKRAApplication

    companion object Instance {

        private var akraApplication: AKRAApplication = null!!

        private fun setInstance(akraApplication: AKRAApplication) {
            this.akraApplication = akraApplication
        }

        fun getInstance(): AKRAApplication {
            return akraApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance.instance = this
    }

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .build()
    }
}
