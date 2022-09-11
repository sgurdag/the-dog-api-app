package com.safagurdag.data.di

import android.content.Context
import com.safagurdag.data.api.thedogapi.TheDogApiClient
import com.safagurdag.data.api.thedogapi.TheDogApiService
import com.safagurdag.data.utils.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideTheDogApiService(
        apiClient: TheDogApiClient
    ): TheDogApiService =
        apiClient.retrofit().create(TheDogApiService::class.java)

    @Provides
    fun provideNetworkUtil(@ApplicationContext context: Context) = NetworkUtil(context)

}