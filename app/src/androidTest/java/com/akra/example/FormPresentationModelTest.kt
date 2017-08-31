package com.akra.example

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.akra.example.user.form.FormInteractor
import com.akra.example.user.form.FormPresentationModel
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as mockWhen

/**
 * Created by Евгений on 8/28/2017.
 */

@RunWith(AndroidJUnit4::class)
@SmallTest
class FormPresentationModelTest {

    @Test
    fun testLoadLabel() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        val formInteractor = mock(FormInteractor::class.java)
        mockWhen(formInteractor.getFormLabel()).thenReturn(Observable.just("0"))
        val formPresentationModel = FormPresentationModel(formInteractor, context)
        val testSubscriber = TestObserver<String>()
        formPresentationModel.init()
        formPresentationModel.getLabelState().subscribe(testSubscriber)
        testSubscriber.onComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertResult("Label: 0")
        verify(formInteractor).getFormLabel()

    }
}
