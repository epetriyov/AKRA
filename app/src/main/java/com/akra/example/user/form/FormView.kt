package com.akra.example.user.form

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Евгений on 8/21/2017.
 */
class FormView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ProgressBar(context, attrs, defStyleAttr) {

    @Inject lateinit var formPresentationModel: FormPresentationModel
    
    private var composite = CompositeDisposable()
    

    init {
        
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        composite.clear()
    }
}
