package kr.leesunr.news.headline

import kr.leesunr.news.headline.ui.HeadlineUiModel

data class HeadlineUiState(
    val isVisibleFetchFailureMessage: Boolean,
    val onClickFetch: () -> Unit,
    val headlines: List<HeadlineUiModel>
)