package kr.leesunr.news.domain.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.leesunr.news.domain.headline.usecase.HeadlineGetUseCase
import kr.leesunr.news.domain.headline.usecase.HeadlineGetUseCaseImpl
import kr.leesunr.news.domain.headline.usecase.HeadlineVisitUseCase
import kr.leesunr.news.domain.headline.usecase.HeadlineVisitUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class HiltModule {

    @Binds
    @Singleton
    abstract fun bindHeadlineUseCase(
        useCase: HeadlineGetUseCaseImpl
    ): HeadlineGetUseCase

    @Binds
    @Singleton
    abstract fun bindHeadlineVisitedUseCase(
        useCase: HeadlineVisitUseCaseImpl
    ): HeadlineVisitUseCase
}