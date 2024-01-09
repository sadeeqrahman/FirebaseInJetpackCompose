package com.sadeeq.encoders.firebaseinjetpackcompose.module

import com.google.firebase.auth.FirebaseAuth
import com.sadeeq.encoders.firebaseinjetpackcompose.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepository(firebaseAuth)
    }

    // You can add more @Provides methods for other dependencies here
}
