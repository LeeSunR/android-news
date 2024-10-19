package kr.leesunr.news.domain.headline.repository

import java.util.Date

interface VisitHistoryRepository {
    suspend fun save(url: String, visitedAt: Date)
}