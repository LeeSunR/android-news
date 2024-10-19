package kr.leesunr.news.data.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.leesunr.news.data.repository.HeadlineRepositoryImpl
import kr.leesunr.news.data.repository.VisitHistoryRepositoryImpl
import kr.leesunr.news.domain.headline.repository.HeadlineRepository
import kr.leesunr.news.domain.headline.repository.VisitHistoryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHeadlineRepository(repository: HeadlineRepositoryImpl): HeadlineRepository

    @Binds
    @Singleton
    abstract fun bindVisitHistoryRepository(repository: VisitHistoryRepositoryImpl): VisitHistoryRepository
}