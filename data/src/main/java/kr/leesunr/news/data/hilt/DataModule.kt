package kr.leesunr.news.data.hilt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.leesunr.news.data.retrofit.OKHttpFactory
import kr.leesunr.news.data.retrofit.RetrofitFactory
import kr.leesunr.news.data.retrofit.api.NewsAPI
import kr.leesunr.news.data.room.AppDatabase
import kr.leesunr.news.data.room.RoomFactory
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
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ) = RetrofitFactory.create(okHttpClient)

    @Singleton
    @Provides
    fun provideNewsAPI(
        retrofit: Retrofit
    ): NewsAPI = retrofit.create(NewsAPI::class.java)

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ):AppDatabase = RoomFactory.create(context)

    @Singleton
    @Provides
    fun provideNewsDao(
        roomDatabase: AppDatabase
    ) = roomDatabase.getHeadlineDAO()

    @Singleton
    @Provides
    fun provideVisitHistoryDAO(
        roomDatabase: AppDatabase
    ) = roomDatabase.getVisitHistoryDAO()
}