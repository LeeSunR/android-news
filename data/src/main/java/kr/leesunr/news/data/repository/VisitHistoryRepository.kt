package kr.leesunr.news.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kr.leesunr.news.data.retrofit.api.NewsAPI
import kr.leesunr.news.data.room.dao.HeadlineDAO
import kr.leesunr.news.data.room.dao.VisitHistoryDAO
import kr.leesunr.news.data.room.dto.VisitHistoryDTO
import kr.leesunr.news.domain.headline.repository.VisitHistoryRepository
import java.util.Date
import javax.inject.Inject

internal class VisitHistoryRepositoryImpl @Inject constructor(
    private val visitHistoryDAO: VisitHistoryDAO,
): VisitHistoryRepository {
    override suspend fun save(url: String, visitedAt: Date) {
        visitHistoryDAO.insert(
            VisitHistoryDTO(
                url = url,
                visitedAt = visitedAt
            )
        )
    }
}