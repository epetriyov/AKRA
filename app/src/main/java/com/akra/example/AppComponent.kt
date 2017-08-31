package com.akra.example

import com.akra.example.user.UserActivity
import com.akra.example.user.form.FormView
import dagger.Component

/**
 * Created by Евгений on 8/21/2017.
 */
@Component(modules = arrayOf(AppModule::class)) interface AppComponent {
    fun inject(formView: FormView)
    fun inject(userActivity: UserActivity)
}
