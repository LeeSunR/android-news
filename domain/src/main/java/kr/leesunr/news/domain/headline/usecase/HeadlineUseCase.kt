package kr.leesunr.news.domain.headline.usecase

import kotlinx.coroutines.flow.Flow
import kr.leesunr.news.domain.headline.entity.Headline

interface HeadlineUseCase {
    suspend fun fetch()
    fun getAllFlow(): Flow<List<Headline>>
}