package kr.leesunr.news.domain.headline.usecase

import kr.leesunr.news.domain.headline.entity.Headline

interface HeadlineVisitUseCase {
    suspend operator fun invoke(headline: Headline)
}