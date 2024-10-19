package kr.leesunr.news.domain.headline.usecase

import kotlinx.coroutines.flow.Flow
import kr.leesunr.news.domain.headline.entity.Headline
import kr.leesunr.news.domain.headline.exception.HeadlineFetchException
import kr.leesunr.news.domain.headline.repository.HeadlineRepository
import kr.leesunr.news.domain.headline.repository.VisitHistoryRepository
import java.time.Instant
import java.util.Date
import javax.inject.Inject

internal class HeadlineUseCaseImpl
@Inject constructor(
    private val headlineRepository: HeadlineRepository,
    private val visitHistoryRepository: VisitHistoryRepository
) : HeadlineUseCase {
    override suspend fun fetch() {
        try {
            headlineRepository.fetch()
        } catch (e: Exception) {
            e.printStackTrace()
            throw HeadlineFetchException(e)
        }
    }

    override suspend fun read(headline: Headline) {
        visitHistoryRepository.save(headline.url, Date.from(Instant.now()))
    }

    override fun getAllFlow(): Flow<List<Headline>> {
        return headlineRepository.getAllFlow()
    }
}