package kr.leesunr.news.data.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kr.leesunr.news.data.retrofit.converter.DateTypeConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "https://newsapi.org/v2/"
    fun create(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(DateTypeConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(getDefaultGson()))
        .build()

    private fun getDefaultGson():Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }
}