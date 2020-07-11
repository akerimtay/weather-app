package com.akerimtay.weatherapp.di.module

import com.akerimtay.weatherapp.BuildConfig
import com.akerimtay.weatherapp.di.scope.NetworkScope
import com.akerimtay.weatherapp.network.interceptor.AuthInterceptor
import com.akerimtay.weatherapp.network.mapping.DateSerializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    @Provides
    @NetworkScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

    @Provides
    @NetworkScope
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Provides
    @NetworkScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, authInterceptor: AuthInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.writeTimeout(10, TimeUnit.SECONDS)
        builder.addInterceptor(loggingInterceptor)
        builder.addInterceptor(authInterceptor)
        return builder.build()
    }

    @Provides
    @NetworkScope
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(Date::class.java, DateSerializer())
        return builder.create()
    }

    @Provides
    @NetworkScope
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.REST_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}