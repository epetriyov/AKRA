package com.akra.example

import com.akra.example.user.form.FormInteractor
import io.reactivex.observers.TestObserver
import org.junit.Test
import java.util.*

/**
 * Created by Евгений on 8/29/2017.
 */
class FormInteractorTest {

    @Test
    fun testGetFormLabel() {
        val formInteractor = FormInteractor()
        val testSubscriber = TestObserver<String>()
        formInteractor.getFormLabel().subscribe(testSubscriber)
        val lock = Object()
        Timer().schedule(object : TimerTask() {
            override fun run() {
                try {
                    testSubscriber.cancel()
                    testSubscriber.assertNoErrors()
                    testSubscriber.assertValues("0", "1", "2")
                } finally {
                    synchronized(lock)
                    {
                        lock.notify()
                    }
                }
            }
        }, 3000L)
        synchronized(lock)
        {
            lock.wait()
        }
    }
}
