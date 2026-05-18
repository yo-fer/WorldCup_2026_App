package com.example.worldcup2026app.core.di

import android.app.Application
import androidx.room.Room
import com.example.worldcup2026app.data.local.MundialDatabase
import com.example.worldcup2026app.data.local.dao.MundialDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMundialDatabase(app: Application): MundialDatabase {
        return Room.databaseBuilder(
            app,
            MundialDatabase::class.java,
            "mundial_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMundialDao(database: MundialDatabase): MundialDao {
        return database.dao
    }
}