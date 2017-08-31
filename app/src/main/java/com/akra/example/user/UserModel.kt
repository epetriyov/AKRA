package com.akra.example.user

import com.akra.example.model.Optional
import com.akra.example.model.User
import com.akra.example.services.CacheService
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

/**
 * Created by Евгений on 8/21/2017.
 */
class UserPresentationModel(var userInteractor: UserInteractor) {

    private val userStateBehavior = BehaviorRelay.create<Optional<User?>>()

    private var composite = CompositeDisposable()

    fun getUserState(): Observable<Optional<User?>> {
        return userStateBehavior.hide()
    }

    fun init() {
        userInteractor.loadUser().subscribe(userStateBehavior).addTo(composite)
    }

    fun dispose() {
        composite.dispose()
    }

    fun saveUser(name: String, surname: String): Observable<Optional<User?>> {
        if (userStateBehavior.value != null) {
            userStateBehavior.value.value?.let {
                userStateBehavior.value.value?.name = name
                userStateBehavior.value.value?.surname = surname
                return userInteractor.saveUser(userStateBehavior.value.value)
            }
        }
        return Observable.just(Optional<User?>(null))
    }
}

open class UserInteractor(val cacheService: CacheService) {

    open fun loadUser(): Observable<Optional<User?>> {
        return cacheService.getUser()
    }

    open fun saveUser(user: User?): Observable<Optional<User?>> {
        cacheService.saveUser(user)
        return Observable.just(Optional<User?>(user))
    }
}

