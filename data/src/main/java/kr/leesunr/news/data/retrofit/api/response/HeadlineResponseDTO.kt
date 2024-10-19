package kr.leesunr.news.data.retrofit.api.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class HeadlineResponseDTO(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<ArticleDTO>
) {
    data class ArticleDTO(
        @SerializedName("source") val source: SourceDTO,
        @SerializedName("author") val author: String,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String?,
        @SerializedName("url") val url: String,
        @SerializedName("urlToImage") val urlToImage: String?,
        @SerializedName("publishedAt") val publishedAt: Date,
        @SerializedName("content") val content: String?
    ) {
        data class SourceDTO(
            @SerializedName("id") val id: String?,
            @SerializedName("name") val name: String
        )
    }
}