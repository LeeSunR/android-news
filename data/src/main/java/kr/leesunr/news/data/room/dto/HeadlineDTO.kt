package kr.leesunr.news.data.room.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.leesunr.news.domain.headline.entity.Headline
import java.util.Date

@Entity(
    tableName = "Headline",
)
internal data class HeadlineDTO(
    @PrimaryKey @ColumnInfo(name = "key") val key: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "publishedAt") val publishedAt: Date,
) {
    fun toDomain(): Headline {
        return Headline(
            key = key,
            title = title,
            url = url,
            imageUrl = imageUrl,
            publishedAt = publishedAt,
            isVisited = false
        )
    }

    companion object {
        fun of(headline: Headline): HeadlineDTO {
            return HeadlineDTO(
                key = headline.key,
                title = headline.title,
                url = headline.url,
                imageUrl = headline.imageUrl,
                publishedAt = headline.publishedAt
            )
        }
    }
}