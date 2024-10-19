package kr.leesunr.news.headline.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kr.leesunr.news.core.base.BaseViewModel
import kr.leesunr.news.domain.headline.entity.Headline
import kr.leesunr.news.domain.headline.usecase.HeadlineUseCase
import kr.leesunr.news.headline.HeadlineUiState
import kr.leesunr.news.headline.ui.HeadlineUiModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(
    private val headlinesUseCase: HeadlineUseCase
) : BaseViewModel() {

    val uiState = headlinesUseCase.getAllFlow().map {
        HeadlineUiState(
            headlines = it.toUiModel()
        )
    }.stateIn(
        scope = baseViewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = HeadlineUiState(emptyList()),
    )

    private fun List<Headline>.toUiModel() = map { it.toUiModel() }

    private fun Headline.toUiModel() = HeadlineUiModel(
        key = key,
        title = title,
        imageUrl = imageUrl,
        publishedDate = publishedAt.toDisplay(),
        onClick = { }
    )

    private fun Date.toDisplay(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(this)
    }

}