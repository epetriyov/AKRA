package com.akra.example

import android.support.test.runner.AndroidJUnit4
import com.akra.example.model.User
import com.akra.example.services.CacheService
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Евгений on 8/28/2017.
 */
@RunWith(AndroidJUnit4::class)
class CacheServiceTest {

    @Test
    fun testSaveItem() {
        val cacheService = CacheService()
        val user = User("", "")
        val subscriber = TestObserver<User>()
        cacheService.saveUser(user).flatMap { cacheService.getUser() }.subscribe(subscriber)
        subscriber.assertNoErrors()
        subscriber.assertResult(user)
    }
}
