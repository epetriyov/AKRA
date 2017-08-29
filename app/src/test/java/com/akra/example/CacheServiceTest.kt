package com.akra.example

import com.akra.example.model.Optional
import com.akra.example.model.User
import com.akra.example.services.CacheService
import io.reactivex.observers.TestObserver
import org.junit.Test

/**
 * Created by Евгений on 8/28/2017.
 */
class CacheServiceTest {

    @Test
    fun testSaveItem() {
        val cacheService = CacheService()
        val user = User("test", "test")
        var subscriber = TestObserver<Optional<User?>>()
        cacheService.saveUser(user).flatMap { cacheService.getUser() }.subscribe(subscriber)
        subscriber.assertNoErrors()
        subscriber.assertResult(Optional(user))
        subscriber = TestObserver<Optional<User?>>()
        cacheService.saveUser(null).flatMap { cacheService.getUser() }.subscribe(subscriber)
        subscriber.assertNoErrors()
        subscriber.assertResult(Optional(null))
    }
}
