package com.akra.example.user

import com.akra.example.model.Optional
import com.akra.example.model.User
import com.akra.example.services.CacheService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Евгений on 8/21/2017.
 */
class UserPresentationModel {


    @Inject lateinit var userInteractor: UserInteractor
}

class UserInteractor(val cacheService: CacheService) {

    init {
//        AKRAApplication.getInstance().component.inject(this)
    }

    fun loadUser(): Observable<Optional<User?>> {
        return cacheService.getUser()
    }

    fun saveUser(user: User?): Observable<Optional<User?>> {
        cacheService.saveUser(user)
        return Observable.just(Optional<User?>(user))
    }
}

