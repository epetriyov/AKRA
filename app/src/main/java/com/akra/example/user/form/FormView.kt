package com.akra.example.user.form

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.akra.example.App
import com.akra.example.R
import com.akra.example.model.Optional
import com.akra.example.model.User
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.view_form.view.*
import javax.inject.Inject

/**
 * Created by Евгений on 8/21/2017.
 */
class FormView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    @Inject lateinit var formPresentationModel: FormPresentationModel

    init {
        App.instance.component.inject(this)
        LayoutInflater.from(context).inflate(R.layout.view_form, this)
    }

    fun nameValue(): Observable<String> {
        return Observable.just(editName.text.toString())
    }

    fun surnameValue(): Observable<String> {
        return Observable.just(editSurname.text.toString())
    }

    fun userConsumer(): Consumer<in Optional<User?>> {
        return Consumer { user ->
            user.value?.let {
                editName.setText(user.value.name)
                editSurname.setText(user.value.surname)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        formPresentationModel.dispose()
    }

    fun surnameChanges(): Observable<String> {
        return editSurname.textChanges().map(CharSequence::toString)
    }

    fun nameChanges(): Observable<String> {
        return editName.textChanges().map(CharSequence::toString)
    }
}
