package com.example.network.di

import com.example.network.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request = chain.request()

                // Log request information
                println("Sending request ${request.url()}")

                // Log request body if present
                request.body()?.let {
                    val buffer = Buffer()
                    it.writeTo(buffer)
                    println("Request body: ${buffer.readUtf8()}")
                }



                // Proceed with the request
                val response = chain.proceed(request)

                // Log response information
                println("Received response for ${response.request().url()}")

                // Log response body if present
                response.body()?.let {
                    val responseBody = it.string()
                    println("Response body: $responseBody")

                    // Re-create the response body, because OkHttp only allows reading it once
                    return response.newBuilder()
                        .addHeader("deps","2")
                        .body(ResponseBody.create(response.body()!!.contentType(), responseBody))
                        .build()
                }

                return response
            }
        }
    }
}