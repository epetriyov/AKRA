package com.akra.example

import android.app.Application

/**
 * Created by Евгений on 8/21/2017.
 */
class AKRAApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .build()
    }
}
