package com.akra.example.user.form

import android.content.Context
import com.akra.example.R
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

/**
 * Created by Евгений on 8/21/2017.
 */
class FormPresentationModel(formInteractor: FormInteractor, context: Context) {

    private var composite = CompositeDisposable()

    private val labelState = BehaviorRelay.create<String>()

    private var context: Context = context

    private var formInteractor: FormInteractor = formInteractor

    fun init() {
        formInteractor.getFormLabel().subscribe(labelState).addTo(composite)
    }

    fun getLabelState(): Observable<String> {
        return labelState.hide().map { it -> context.resources.getString(R.string.label_format, it) }
    }

    fun dispose() {
        composite.dispose()
    }
}

open class FormInteractor {
    open fun getFormLabel(): Observable<String> {
        return Observable.interval(1, TimeUnit.SECONDS).map(Long::toString)
    }

}
