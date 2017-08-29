package com.akra.example

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.akra.example.user.form.FormInteractor
import com.akra.example.user.form.FormPresentationModel
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as mockWhen

/**
 * Created by Евгений on 8/28/2017.
 */

@RunWith(AndroidJUnit4::class)
class FormPresentationModelTest {

    @Test
    fun testLoadLabel() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        var formInteractorMock = Mockito.mock(FormInteractor::class.java)
        mockWhen(formInteractorMock.getFormLabel()).thenReturn(Observable.just(""))
        val formPresentationModel = FormPresentationModel(formInteractorMock, context)
        val testSubscriber = TestObserver<String>()
        formPresentationModel.init()
        formPresentationModel.getLabelState().subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(context.resources.getString(R.string.label_format, ""))
        verify(formInteractorMock).getFormLabel()
    }
}
