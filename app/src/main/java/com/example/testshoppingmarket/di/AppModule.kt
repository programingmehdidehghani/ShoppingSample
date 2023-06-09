package com.example.testshoppingmarket.di

import android.content.Context
import androidx.room.Room
import com.example.testshoppingmarket.App
import com.example.testshoppingmarket.api.ApiService
import com.example.testshoppingmarket.db.CartDB
import com.example.testshoppingmarket.utils.Constants.Companion.BASE_URL
import com.example.testshoppingmarket.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun retrofit() : ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        CartDB::class.java,
        DATABASE_NAME
    ).build()

}