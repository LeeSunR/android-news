package kr.leesunr.news.headline.ui

class HeadlineUiModel(
    val key: String,
    val imageUrl: String?,
    val title: String,
    val publishedDate: String,
    val onClick: () -> Unit
)