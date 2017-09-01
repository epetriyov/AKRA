package com.akra.example

import android.support.test.InstrumentationRegistry
import android.support.test.annotation.UiThreadTest
import android.support.test.rule.UiThreadTestRule
import android.support.test.runner.AndroidJUnit4
import com.akra.example.user.action.ControllView
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertFalse

/**
 * Created by Евгений on 8/28/2017.
 */
@RunWith(AndroidJUnit4::class)
open class ControllViewTest {

    @Rule @JvmField  var uiThread = UiThreadTestRule()

    @UiThreadTest
    @Test
    fun testButtonClicks() {
        val controllView = ControllView(InstrumentationRegistry.getInstrumentation().targetContext)
        Observable.just(false).subscribe(controllView.getEnableConsumer())
        assertFalse(controllView.isEnabled)
        val testSubscriber = TestObserver<Unit>()
        controllView.clicks().subscribe(testSubscriber)
        controllView.performClick()
        testSubscriber.assertNoErrors()
        assertEquals(1, testSubscriber.valueCount())
    }
}
