package com.seda.artbookk.di

import android.content.Context
import androidx.room.Room
import com.seda.artbookk.api.RetrofitApi
import com.seda.artbookk.roomdb.ArtDatabase
import com.seda.artbookk.unit.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideArtDatabase(@ApplicationContext context : Context)=
        Room.databaseBuilder(context,ArtDatabase::class.java,"arts" ).build()

    @Singleton
    @Provides
    fun provideArtDao(db:ArtDatabase)= db.artDao()

    @Singleton
    @Provides
    fun provideBaseUrl() = Util.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): RetrofitApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)

}