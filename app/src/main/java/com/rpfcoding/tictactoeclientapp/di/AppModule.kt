package com.rpfcoding.tictactoeclientapp.di

import com.rpfcoding.tictactoeclientapp.data.KtorRealtimeMessagingClient
import com.rpfcoding.tictactoeclientapp.data.RealtimeMessagingClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
        }
    }

    @Provides
    @Singleton
    fun provideRealtimeMessagingClient(
        httpClient: HttpClient
    ): RealtimeMessagingClient {
        return KtorRealtimeMessagingClient(httpClient)
    }
}