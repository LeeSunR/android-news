package kr.leesunr.news.data.retrofit

import kr.leesunr.news.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OKHttpFactory {
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 30L
    fun create():OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(CONNECT_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .build()
    }

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}