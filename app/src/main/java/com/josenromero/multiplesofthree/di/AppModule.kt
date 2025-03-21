package com.josenromero.multiplesofthree.di

import android.content.Context
import androidx.room.Room
import com.josenromero.multiplesofthree.data.player.PlayerDao
import com.josenromero.multiplesofthree.data.player.PlayerDataBase
import com.josenromero.multiplesofthree.data.preferences.PreferencesDao
import com.josenromero.multiplesofthree.domain.AddNumberToBoardGame
import com.josenromero.multiplesofthree.domain.Audio
import com.josenromero.multiplesofthree.domain.CheckAnswer
import com.josenromero.multiplesofthree.domain.CreateBoardGame
import com.josenromero.multiplesofthree.domain.NextStage
import com.josenromero.multiplesofthree.domain.RemoveNumberToBoardGame
import com.josenromero.multiplesofthree.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesPlayerRoomDatabase(@ApplicationContext app: Context): PlayerDataBase {

        // create database
        return Room.databaseBuilder(
            app,
            PlayerDataBase::class.java,
            Constants.PLAYER_DATABASE
        ).build()

    }

    @Provides
    @Singleton
    fun providesPlayerDao(playerDataBase: PlayerDataBase): PlayerDao {
        return playerDataBase.playerDao()
    }
    @Provides
    @Singleton
    fun providesPreferencesDao(playerDataBase: PlayerDataBase): PreferencesDao {
        return playerDataBase.preferencesDao()
    }

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
    fun provideRemoveNumberToBoardGame(checkAnswer: CheckAnswer): RemoveNumberToBoardGame {
        return RemoveNumberToBoardGame(checkAnswer = checkAnswer)
    }

    @Provides
    @Singleton
    fun provideAudio(@ApplicationContext app: Context): Audio {
        return Audio(app)
    }

    @Provides
    @Singleton
    fun provideCheckAnswer(): CheckAnswer {
        return CheckAnswer()
    }

    @Provides
    @Singleton
    fun provideNextStage(): NextStage {
        return NextStage()
    }

}