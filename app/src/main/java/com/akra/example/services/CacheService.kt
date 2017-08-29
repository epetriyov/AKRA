package com.akra.example.services

import com.akra.example.model.Optional
import com.akra.example.model.User
import io.reactivex.Observable

/**
 * Created by Евгений on 8/28/2017.
 */
class CacheService {
    fun saveUser(user: User?): Observable<Optional<User?>> {
        CacheStorage.INSTANCE.user = Optional<User?>(user)
        return Observable.just(Optional<User?>(user))
    }

    fun getUser(): Observable<Optional<User?>> {
        return Observable.just(CacheStorage.INSTANCE.user)
    }
}

enum class CacheStorage {

    INSTANCE;

    var user: Optional<User?> = Optional(null)

}
