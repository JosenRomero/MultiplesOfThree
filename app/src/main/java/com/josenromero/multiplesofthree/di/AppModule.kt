package com.josenromero.multiplesofthree.di

import com.josenromero.multiplesofthree.domain.AddNumberToBoardGame
import com.josenromero.multiplesofthree.domain.CreateBoardGame
import com.josenromero.multiplesofthree.domain.RemoveNumberToBoardGame
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
    fun provideCreateBoardGame(addNumberToBoard: AddNumberToBoardGame): CreateBoardGame {
        return CreateBoardGame(addNumberToBoard)
    }

    @Provides
    @Singleton
    fun provideAddNumberToBoardGame(): AddNumberToBoardGame {
        return AddNumberToBoardGame()
    }

    @Provides
    @Singleton
    fun provideRemoveNumberToBoardGame(): RemoveNumberToBoardGame {
        return RemoveNumberToBoardGame()
    }

}