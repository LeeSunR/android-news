package kr.leesunr.news.data.retrofit.api

import kr.leesunr.news.data.retrofit.api.response.HeadlineResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query


internal interface NewsAPI {
    @GET("/v2/top-headlines")
    suspend fun getHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String = "us"
    ): HeadlineResponseDTO
}