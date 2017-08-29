package com.akra.example

import com.akra.example.model.Optional
import com.akra.example.model.User
import com.akra.example.services.CacheService
import com.akra.example.user.UserInteractor
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.internal.verification.VerificationModeFactory.times
import org.mockito.Mockito.`when` as mockWhen


/**
 * Created by Евгений on 8/28/2017.
 */
class UserInteractorTest {

    @Test
    fun testLoadUser() {
        var cacheMock = mock(CacheService::class.java)
        val userInteractor = UserInteractor(cacheMock)
        mockWhen(cacheMock.getUser()).thenReturn(Observable.just(Optional<User?>(null)))
        var testSubscriber = TestObserver<Optional<User?>>()
        userInteractor.loadUser().subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(Optional<User?>(null))
        Mockito.verify(cacheMock).getUser()
        mockWhen(cacheMock.getUser()).thenReturn(Observable.just(Optional<User?>(User("test", "test"))))
        testSubscriber = TestObserver<Optional<User?>>()
        userInteractor.loadUser().subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(Optional<User?>(User("test", "test")))
        Mockito.verify(cacheMock, times(2)).getUser()
    }

    @Test
    fun testSaveUser() {
        var cacheMock = mock(CacheService::class.java)
        val userInteractor = UserInteractor(cacheMock)
        val testUser = User("test", "test")
        mockWhen(cacheMock.saveUser(ArgumentMatchers.any())).thenReturn(Observable.just(Optional<User?>(null)))
        var testSubscriber = TestObserver<Optional<User?>>()
        userInteractor.saveUser(testUser).subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(Optional<User?>(testUser))
        Mockito.verify(cacheMock).saveUser(testUser)
    }
}
