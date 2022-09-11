package com.safagurdag.data.di

import com.safagurdag.data.repository.TheDogApiRepository
import com.safagurdag.domain.repository.ITheDogApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideTheDogApiRepository(repository: TheDogApiRepository): ITheDogApiRepository
}