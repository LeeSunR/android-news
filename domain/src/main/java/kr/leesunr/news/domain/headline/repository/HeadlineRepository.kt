package kr.leesunr.news.domain.headline.repository

import kotlinx.coroutines.flow.Flow
import kr.leesunr.news.domain.headline.entity.Headline

interface HeadlineRepository {
    fun getAllFlow(): Flow<List<Headline>>
}