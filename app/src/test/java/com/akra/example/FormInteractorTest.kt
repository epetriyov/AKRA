package com.akra.example

import com.akra.example.user.form.FormInteractor
import io.reactivex.observers.TestObserver
import org.junit.Test

/**
 * Created by Евгений on 8/29/2017.
 */
class FormInteractorTest {

    @Test
    fun testGetFormLabel() {
        val formInteractor = FormInteractor()
        val testSubscriber = TestObserver<String>()
        formInteractor.getFormLabel().subscribe(testSubscriber)
        testSubscriber.awaitCount(3)
        testSubscriber.onComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValues("0", "1", "2")
    }
}
