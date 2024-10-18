package kr.leesunr.news.data.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.leesunr.news.data.retrofit.OKHttpFactory
import kr.leesunr.news.data.retrofit.RetrofitFactory
import kr.leesunr.news.data.retrofit.api.NewsAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    /**
     * interceptor
     * */
    @Singleton
    @Provides
    fun provideOkHttpClient() = OKHttpFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = RetrofitFactory.create(okHttpClient)

    @Singleton
    @Provides
    fun provideNewsAPI(retrofit: Retrofit) = retrofit.create(NewsAPI::class.java)
}