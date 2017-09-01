package com.akra.example

import android.support.test.runner.AndroidJUnit4
import com.akra.example.model.Optional
import com.akra.example.model.User
import com.akra.example.user.UserInteractor
import com.akra.example.user.UserPresentationModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.mock
import org.mockito.internal.verification.Times
import org.mockito.Mockito.`when` as mockWhen

/**
 * Created by Евгений on 8/28/2017.
 */
@RunWith(AndroidJUnit4::class)
class UserPresentationModelTest {

    @Test
    fun testGetUserState() {
        val interactorMock = mock(UserInteractor::class.java)
        mockWhen(interactorMock.loadUser()).thenReturn(Observable.just(Optional<User?>(null)))
        val userPresentationModel = UserPresentationModel(interactorMock)
        val testSubscriber = TestObserver<Optional<User?>>()
        userPresentationModel.getUserState().subscribe(testSubscriber)
        testSubscriber.onComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(Optional<User?>(null))
        verify(interactorMock).loadUser()
    }

    @Test
    fun testOnSaveButtonClicked() {
        val interactorMock = mock(UserInteractor::class.java)
        mockWhen(interactorMock.loadUser()).thenReturn(Observable.just(Optional<User?>(User("", ""))))
        mockWhen(interactorMock.saveUser(ArgumentMatchers.any()))
                .thenAnswer { invocation -> Observable.just(Optional<User?>(invocation!!.arguments[0] as User?)) }
        val userPresentationModel = UserPresentationModel(interactorMock)
        var testSubscriber = TestObserver<Optional<User?>>()
        userPresentationModel.saveUser("", "").subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(Optional<User?>(null))
        verify(interactorMock, Times(0)).saveUser(any())
        testSubscriber = TestObserver<Optional<User?>>()
        userPresentationModel.saveUser("name", "surname").subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        val user = User("name", "surname")
        testSubscriber.assertResult(Optional<User?>(user))
        verify(interactorMock).saveUser(user)
    }
}
