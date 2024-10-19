package kr.leesunr.news.domain.headline.usecase

import kotlinx.coroutines.flow.Flow
import kr.leesunr.news.domain.headline.entity.Headline
import kr.leesunr.news.domain.headline.repository.HeadlineRepository
import javax.inject.Inject

internal class HeadlineUseCaseImpl
@Inject constructor(
    private val headlineRepository: HeadlineRepository,
) : HeadlineUseCase {
    override fun getAllFlow(): Flow<List<Headline>> {
        return headlineRepository.getAllFlow()
    }
}