package com.example_app.pricyapp.retrofit.gold

import com.example_app.pricyapp.retrofit.time.TimeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://tools.daneshjooyar.com/api/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideGoldApiService(retrofit: Retrofit): GoldApiService = retrofit.create(GoldApiService::class.java)
    @Provides
    @Singleton
    fun provideTimeApiService(retrofit: Retrofit): TimeApiService = retrofit.create(TimeApiService::class.java)
}