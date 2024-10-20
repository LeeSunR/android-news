package kr.leesunr.news.domain.headline.repository

import kotlinx.coroutines.flow.Flow
import kr.leesunr.news.domain.headline.entity.Headline

interface HeadlineRepository {
    suspend fun fetch()
    fun getAllFlow(): Flow<List<Headline>>
}