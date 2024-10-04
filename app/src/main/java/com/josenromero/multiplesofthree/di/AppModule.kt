package com.josenromero.multiplesofthree.di

import com.josenromero.multiplesofthree.domain.CreateBoardGame
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideCreateBoardGame(): CreateBoardGame {
        return CreateBoardGame()
    }

}