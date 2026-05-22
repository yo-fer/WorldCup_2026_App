package com.example.worldcup2026app.core.di

import com.example.worldcup2026app.BuildConfig
import com.example.worldcup2026app.core.network.NetworkConstants
import com.example.worldcup2026app.data.remote.MundialApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // para el debbug
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // interceptor para la api key
        val headerInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()

            val requestBuilder = originalRequest.newBuilder()
                .header("x-apisports-key", BuildConfig.API_SPORTS_KEY)
                .header("x-rapidapi-host", "v3.football.api-sports.io")

            chain.proceed(requestBuilder.build())
        }

        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMundialApiService(client: OkHttpClient): MundialApiService {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.TEST_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MundialApiService::class.java)
    }
}