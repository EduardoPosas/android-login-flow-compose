package com.example.loginflowapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.loginflowapp.auth.data.remote.AuthServiceApi
import com.example.loginflowapp.datastore.UserPreferences
import com.example.loginflowapp.datastore.data.UserPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    private const val DATA_STORE_FILE_NAME = "user_prefs.pb"

    @Provides
    @Singleton
    fun provideAuthServiceApi(): AuthServiceApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(AuthServiceApi.BASE_URL)
            .client(client)
            .build()
            .create(AuthServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSingleProcessDataStore(
        @ApplicationContext context: Context,
        userPreferencesSerializer: UserPreferencesSerializer
    ): DataStore<UserPreferences> = DataStoreFactory.create(
        serializer = userPreferencesSerializer
    ) {
        context.dataStoreFile(DATA_STORE_FILE_NAME)
    }
}