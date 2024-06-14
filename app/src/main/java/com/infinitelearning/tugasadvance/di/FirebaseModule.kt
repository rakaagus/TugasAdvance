package com.infinitelearning.tugasadvance.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.infinitelearning.tugasadvance.data.repository.FireBaseAuthImpl
import com.infinitelearning.tugasadvance.domain.repository.FireBaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModule {

    @Binds
    @Singleton
    abstract fun provideFirebaseAuth(fireBaseAuthImpl: FireBaseAuthImpl) : FireBaseAuth

    companion object{
        @Provides
        @Singleton
        fun provideSingInClient(@ApplicationContext context: Context): SignInClient{
            return Identity.getSignInClient(context)
        }
    }
}