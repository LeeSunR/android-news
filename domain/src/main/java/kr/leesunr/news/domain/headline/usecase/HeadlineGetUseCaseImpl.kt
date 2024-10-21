package kr.leesunr.news.domain.headline.usecase

import kotlinx.coroutines.flow.Flow
import kr.leesunr.news.domain.headline.entity.Headline
import kr.leesunr.news.domain.headline.exception.HeadlineFetchException
import kr.leesunr.news.domain.headline.repository.HeadlineRepository
import javax.inject.Inject

internal class HeadlineGetUseCaseImpl
@Inject constructor(
    private val headlineRepository: HeadlineRepository
) : HeadlineGetUseCase {
    override suspend fun fetch() {
        try {
            headlineRepository.fetch()
        } catch (e: Exception) {
            e.printStackTrace()
            throw HeadlineFetchException(e)
        }
    }

    override fun getAllFlow(): Flow<List<Headline>> {
        return headlineRepository.getAllFlow()
    }
}