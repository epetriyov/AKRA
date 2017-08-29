package com.akra.example

import com.akra.example.user.UserInteractor
import com.akra.example.user.UserPresentationModel
import com.akra.example.user.action.ControllPresentationModel
import com.akra.example.user.form.FormPresentationModel
import dagger.Module
import dagger.Provides

/**
 * Created by Евгений on 8/21/2017.
 */
@Module class AppModule {
    @Provides fun provideButtonPresentationModel(): ControllPresentationModel {
        return ControllPresentationModel()
    }

    @Provides fun provideProgressBarPresentationModel(): FormPresentationModel {
        return FormPresentationModel()
    }

    @Provides fun provideUserInteractor(): UserInteractor {
        return UserInteractor()
    }

    @Provides fun provideModulesPresentationModel(): UserPresentationModel {
        return UserPresentationModel()
    }
}
