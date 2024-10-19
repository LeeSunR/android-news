package kr.leesunr.news.domain.headline.entity

import java.time.Instant
import java.util.Date

/**
 * key = sourceName + title + publishedAt + url
 * */
data class Headline(
    val key: String,
    val title: String,
    val url: String,
    val imageUrl: String?,
    val publishedAt: Date,
    val isVisited: Boolean,
) {
}