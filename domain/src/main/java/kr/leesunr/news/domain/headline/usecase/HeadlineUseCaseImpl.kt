package kr.leesunr.news.domain.headline.usecase

import kotlinx.coroutines.flow.Flow
import kr.leesunr.news.domain.headline.entity.Headline
import kr.leesunr.news.domain.headline.repository.HeadlineRepository
import java.time.Instant
import java.util.Date
import javax.inject.Inject

internal class HeadlineUseCaseImpl
@Inject constructor(
    private val headlineRepository: HeadlineRepository,
) : HeadlineUseCase {
    override suspend fun fetch() {
        headlineRepository.fetch()
    }

    override suspend fun read(headline: Headline) {
        val newHeadline = headline.copy(
            lastViewedAt = Date.from(Instant.now())
        )
        headlineRepository.update(newHeadline)
    }

    override fun getAllFlow(): Flow<List<Headline>> {
        return headlineRepository.getAllFlow()
    }
}