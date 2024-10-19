package kr.leesunr.news.data.room.dto

import androidx.room.Embedded
import androidx.room.Relation
import kr.leesunr.news.domain.headline.entity.Headline

internal data class HeadlineWithIsVisitHistoryDTO(
    @Embedded val headlineDTO: HeadlineDTO,

    @Relation(
        parentColumn = "url",
        entityColumn = "url"
    )
    val visitHistories: List<VisitHistoryDTO>
) {
    fun toDomain(): Headline {
        return Headline(
            key = headlineDTO.key,
            title = headlineDTO.title,
            url = headlineDTO.url,
            imageUrl = headlineDTO.imageUrl,
            publishedAt = headlineDTO.publishedAt,
            isVisited = visitHistories.isNotEmpty()
        )
    }
}