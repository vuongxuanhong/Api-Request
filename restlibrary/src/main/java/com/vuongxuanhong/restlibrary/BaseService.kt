package com.vuongxuanhong.restlibrary

import android.app.Application
import com.vuongxuanhong.restlibrary.auth.AppAuthenticator
import com.vuongxuanhong.restlibrary.config.Configuration
import com.vuongxuanhong.restlibrary.service.ApiService
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by vuongxuanhong on 9/5/18. HappyCoding!
 */
class BaseService(private val config: Configuration, private val app: Application) {

    private val cache: Cache by lazy {
        val size: Long = config.cacheSizeInMB() * 1024L * 1024 // 10Mb
        okhttp3.Cache(app.cacheDir, size)
    }

    private val logger: Interceptor by lazy {
        val logging = HttpLoggingInterceptor()
        val requestLogLevel = config.requestLogLevel()
        when (requestLogLevel) {
            Configuration.Level.BODY -> {
                logging.level = HttpLoggingInterceptor.Level.BODY
            }
            Configuration.Level.HEADERS -> {
                logging.level = HttpLoggingInterceptor.Level.HEADERS
            }
            Configuration.Level.NONE -> {
                logging.level = HttpLoggingInterceptor.Level.NONE
            }
            else -> {
                // default
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }
        }

        logging
    }

    private val cacheInterceptor: Interceptor by lazy {
        // cache response for 1 minutes
        Interceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                    .cacheControl(CacheControl.Builder()
                            .maxAge(config.cacheTimeoutInSecs(), TimeUnit.SECONDS)
                            .build())
                    .build()

            return@Interceptor chain.proceed(request)
        }
    }

    private val auth: Authenticator = config.authenticator()

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(config.connectionTimeoutInSecs().toLong(), TimeUnit.SECONDS)
                .readTimeout(config.connectionTimeoutInSecs().toLong(), TimeUnit.SECONDS)
                .authenticator(auth)
                .addInterceptor(logger)
                .addInterceptor(cacheInterceptor)
                .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("http://google.com")
                .build()
    }

    val apiService: ApiService by lazy {
        val service = retrofit.create(ApiService::class.java)
        if (auth is AppAuthenticator) {
            auth.service = service
        }

        service

    }

}