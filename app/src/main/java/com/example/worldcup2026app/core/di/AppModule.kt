package com.example.worldcup2026app.core.di

import com.example.worldcup2026app.data.local.dao.MundialDao
import com.example.worldcup2026app.data.remote.MundialApiService
import com.example.worldcup2026app.data.repository.PlayerRepositoryImpl
import com.example.worldcup2026app.data.repository.TeamRepositoryImpl
import com.example.worldcup2026app.domain.repository.PlayerRepository
import com.example.worldcup2026app.domain.repository.TeamRepository
import com.example.worldcup2026app.domain.usecase.GetTeamsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTeamRepository(
        api: MundialApiService,
        dao: MundialDao
    ): TeamRepository {
        return TeamRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun providePlayerRepository(
        api: MundialApiService,
        dao: MundialDao
    ): PlayerRepository {
        return PlayerRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetTeamsUseCase(repository: TeamRepository): GetTeamsUseCase {
        return GetTeamsUseCase(repository)
    }
}