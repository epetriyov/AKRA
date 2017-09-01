package com.akra.example

import android.support.test.InstrumentationRegistry
import android.support.test.rule.UiThreadTestRule
import android.support.test.runner.AndroidJUnit4
import com.akra.example.model.Optional
import com.akra.example.model.User
import com.akra.example.user.form.FormView
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



/**
 * Created by Евгений on 8/28/2017.
 */
@RunWith(AndroidJUnit4::class)
class FormViewTest {

    @Rule @JvmField val uiThread = UiThreadTestRule()

    @Test
    fun testFormChanges() {
        val formView = FormView(InstrumentationRegistry.getInstrumentation().targetContext)
        val nameSubscriber = TestObserver<String>()
        formView.nameChanges().subscribe(nameSubscriber)
        val surnameSubscriber = TestObserver<String>()
        formView.surnameChanges().subscribe(surnameSubscriber)
        Observable.just(Optional<User?>(User("name", "surname"))).subscribe(formView.userConsumer())
        nameSubscriber.assertNoErrors()
        surnameSubscriber.assertNoErrors()
        nameSubscriber.assertValue("name")
        surnameSubscriber.assertValue("surname")
    }
}
