package com.akra.example

import com.akra.example.user.UserInteractor
import com.akra.example.user.action.ControllPresentationModel
import com.akra.example.user.action.ControllView
import com.akra.example.user.form.FormPresentationModel
import com.akra.example.user.form.FormView
import dagger.Component

/**
 * Created by Евгений on 8/21/2017.
 */
@Component(modules = arrayOf(AppModule::class)) interface AppComponent {
    fun inject(formView: FormView)
    fun inject(controllView: ControllView)
    fun inject(formPresentationModel: FormPresentationModel)
    fun inject(controllPresentationModel: ControllPresentationModel)
    fun inject(userInteractor: UserInteractor)
}
