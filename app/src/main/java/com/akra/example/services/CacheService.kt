package com.akra.example.services

import com.akra.example.model.User
import io.reactivex.Observable

/**
 * Created by Евгений on 8/28/2017.
 */
class CacheService {
    fun saveUser(user: User): Observable<User> {
        return Observable.just(user)
    }

    fun getUser(): Observable<User> {
        return Observable.just(User("", ""))
    }


}
