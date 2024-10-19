package kr.leesunr.news.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.leesunr.news.data.BuildConfig
import kr.leesunr.news.data.retrofit.api.NewsAPI
import kr.leesunr.news.domain.headline.entity.Headline
import kr.leesunr.news.domain.headline.repository.HeadlineRepository
import java.util.Base64
import javax.inject.Inject

internal class HeadlineRepositoryImpl
@Inject constructor(
    private val newsAPI: NewsAPI
) : HeadlineRepository {
    override fun getAllFlow(): Flow<List<Headline>> = flow {
        val response = newsAPI.getHeadlines(apiKey = BuildConfig.NEWS_API_KEY)
        val headlines = response.articles.map {
            val keySource = it.source.name + it.title + it.publishedAt + it.url
            val key = Base64.getEncoder().encodeToString(keySource.toByteArray())
            Headline(
                key = key,
                title = it.title,
                url = it.url,
                imageUrl = it.urlToImage,
                publishedAt = it.publishedAt,
                sourceName = it.source.name,
                lastViewedAt = null
            )
        }
        emit(headlines)
    }
}