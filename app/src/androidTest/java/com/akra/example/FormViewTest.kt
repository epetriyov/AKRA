package com.akra.example

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.akra.example.user.form.FormView
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Евгений on 8/28/2017.
 */
@RunWith(AndroidJUnit4::class)
class FormViewTest {

    @Test
    fun testFormChanges() {
        val formView = FormView(InstrumentationRegistry.getInstrumentation().targetContext)
    }
}
