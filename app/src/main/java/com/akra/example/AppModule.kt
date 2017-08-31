package com.akra.example

import android.content.Context
import com.akra.example.services.CacheService
import com.akra.example.user.UserInteractor
import com.akra.example.user.UserPresentationModel
import com.akra.example.user.form.FormInteractor
import com.akra.example.user.form.FormPresentationModel
import dagger.Module
import dagger.Provides

/**
 * Created by Евгений on 8/21/2017.
 */
@Module class AppModule(context: Context) {

    private val context: Context = context

    @Provides fun provideContext(): Context {
        return context
    }

    @Provides fun provideFormPresentationModel(formInteractor: FormInteractor, context: Context): FormPresentationModel {
        return FormPresentationModel(formInteractor, context)
    }

    @Provides fun provideFormInteractor(): FormInteractor {
        return FormInteractor()
    }


    @Provides fun provideCacheService(): CacheService {
        return CacheService()
    }

    @Provides fun provideUserInteractor(cacheService: CacheService): UserInteractor {
        return UserInteractor(cacheService)
    }

    @Provides fun provideModulesPresentationModel(userInteractor: UserInteractor): UserPresentationModel {
        return UserPresentationModel(userInteractor)
    }
}
