package kr.leesunr.news.domain.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.leesunr.news.domain.headline.usecase.HeadlineUseCase
import kr.leesunr.news.domain.headline.usecase.HeadlineUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class HiltModule {

    @Binds
    @Singleton
    abstract fun bindHeadlineUseCase(
        useCase: HeadlineUseCaseImpl
    ): HeadlineUseCase
}