package com.akra.example.user.form

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.akra.example.App
import com.akra.example.R
import com.akra.example.model.Optional
import com.akra.example.model.User
import com.jakewharton.rxbinding2.widget.text
import com.jakewharton.rxbinding2.widget.textChanges
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.view_form.view.*
import javax.inject.Inject

/**
 * Created by Евгений on 8/21/2017.
 */
class FormView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    @Inject lateinit var formPresentationModel: FormPresentationModel

    private val nameState = PublishRelay.create<String>()

    private val surnameState = PublishRelay.create<String>()

    init {
        orientation = VERTICAL
        App.instance.component.inject(this)
        LayoutInflater.from(context).inflate(R.layout.view_form, this)
        editSurname.textChanges().map(CharSequence::toString).subscribe(surnameState)
        editName.textChanges().map(CharSequence::toString).subscribe(nameState)
        formPresentationModel.getLabelState().observeOn(AndroidSchedulers.mainThread()).subscribe(label.text())
    }

    fun userConsumer(): Consumer<in Optional<User?>> {
        return Consumer { user ->
            user.value?.let {
                editName.setText(user.value!!.name)
                editSurname.setText(user.value.surname)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        formPresentationModel.dispose()
    }

    fun surnameChanges(): Observable<String> {
        return surnameState.hide()
    }

    fun nameChanges(): Observable<String> {
        return nameState.hide()
    }

    fun surnameValue(): String {
        return editSurname.text.toString()
    }

    fun nameValue(): String {
        return editName.text.toString()
    }
}
