package com.akra.example.user.action

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Евгений on 8/21/2017.
 */
class ControllView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Button(context, attrs, defStyleAttr) {

    @Inject lateinit var controllPresentationModel: ControllPresentationModel

    private var composite = CompositeDisposable()


    init {
        
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        composite.add(clicks().subscribe())
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        composite.clear()
    }
}
