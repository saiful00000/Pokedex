package com.shaiful.pokedex.di

import com.shaiful.pokedex.data.remote.PokeApi
import com.shaiful.pokedex.repository.PokemonRepository
import com.shaiful.pokedex.util.ApiUrls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*
    * Pokemon repository provider
    * */
    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokeApi
    ) = PokemonRepository(api)





    /*
    * Pokemon api/retrofit provider
    * */
    @Provides
    @Singleton
    fun providePokeApi(): PokeApi {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor = loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiUrls.baseUrl)
            .client(client)
            .build()
            .create(PokeApi::class.java)
    }

}