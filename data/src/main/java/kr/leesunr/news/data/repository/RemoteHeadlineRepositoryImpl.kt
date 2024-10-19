package kr.leesunr.news.data.repository

import android.content.Context
import com.bumptech.glide.Glide
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.leesunr.news.data.BuildConfig
import kr.leesunr.news.data.retrofit.api.NewsAPI
import kr.leesunr.news.data.retrofit.api.response.HeadlineResponseDTO
import kr.leesunr.news.data.room.dao.HeadlineDAO
import kr.leesunr.news.data.room.dto.HeadlineDTO
import kr.leesunr.news.domain.headline.entity.Headline
import kr.leesunr.news.domain.headline.repository.HeadlineRepository
import java.util.Base64
import javax.inject.Inject

internal class HeadlineRepositoryImpl
@Inject constructor(
    private val newsAPI: NewsAPI,
    private val headlineDAO: HeadlineDAO,
    @ApplicationContext private val context: Context
) : HeadlineRepository {
    override suspend fun fetch() {
        val response = newsAPI.getHeadlines(apiKey = BuildConfig.NEWS_API_KEY)
        val headlines = response.toHeadlines()

        //image pre load
        headlines.forEach {
            val imageUrl = it.imageUrl
            if (imageUrl != null) imagesPreLoad(imageUrl)
        }

        val headlineDTOs = headlines.map { HeadlineDTO.of(it) }
        headlineDAO.insertAll(headlineDTOs)
    }

    override suspend fun update(headline: Headline) {
        val headlineDTO = HeadlineDTO.of(headline)
        headlineDAO.update(headlineDTO)
    }

    override fun getAllFlow(): Flow<List<Headline>> {
        val flow = headlineDAO.getAll().map { headlineDTOs ->
            headlineDTOs.map { it.toDomain() }
        }
        return flow
    }

    private fun HeadlineResponseDTO.toHeadlines(): List<Headline> {
        return this.articles.map {
            val keySource = it.source.name + it.title + it.publishedAt + it.url
            val key = Base64.getEncoder().encodeToString(keySource.toByteArray())
            Headline(
                key = key,
                title = it.title,
                url = it.url,
                imageUrl = it.urlToImage,
                publishedAt = it.publishedAt,
                lastViewedAt = null
            )
        }
    }

    private fun imagesPreLoad(url: String) {
        Glide.with(context)
            .load(url)
            .preload()
    }
}