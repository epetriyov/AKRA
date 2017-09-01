package com.akra.example.user.action

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import com.akra.example.R
import com.jakewharton.rxbinding2.view.enabled
import io.reactivex.functions.Consumer

/**
 * Created by Евгений on 8/21/2017.
 */
class ControllView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Button(context, attrs, defStyleAttr) {


    init {
        setText(R.string.save)
        isEnabled = false
    }

    fun getEnableConsumer(): Consumer<in Boolean> {
        return enabled()
    }
}
