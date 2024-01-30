package com.example.photonassesment.di

import com.example.photonassesment.model.data.ApiService
import com.example.photonassesment.model.data.ApiService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitInstance {

    @Provides
    @Singleton
    fun provideLoggingInterceptorOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .build();
        return client
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideSchoolApiService(
        retrofit: Retrofit
    ): ApiService.Service {
        return retrofit.create(ApiService.Service::class.java)
    }

}