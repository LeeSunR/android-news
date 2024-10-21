package kr.leesunr.news.domain.headline.usecase

import kr.leesunr.news.domain.headline.entity.Headline
import kr.leesunr.news.domain.headline.repository.VisitHistoryRepository
import java.time.Instant
import java.util.Date
import javax.inject.Inject

internal class HeadlineVisitUseCaseImpl
@Inject constructor(
    private val visitHistoryRepository: VisitHistoryRepository
) : HeadlineVisitUseCase {
    override suspend fun invoke(headline: Headline) {
        val now = Date.from(Instant.now())
        visitHistoryRepository.save(headline.url, now)
    }
}