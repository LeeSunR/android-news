package kr.leesunr.news.headline.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kr.leesunr.news.core.base.BaseViewModel
import kr.leesunr.news.core.base.command.Destination
import kr.leesunr.news.core.base.command.Key
import kr.leesunr.news.core.base.command.NavigationCommand
import kr.leesunr.news.domain.headline.entity.Headline
import kr.leesunr.news.domain.headline.exception.HeadlineFetchException
import kr.leesunr.news.domain.headline.usecase.HeadlineVisitUseCase
import kr.leesunr.news.domain.headline.usecase.HeadlineGetUseCase
import kr.leesunr.news.headline.HeadlineUiState
import kr.leesunr.news.headline.ui.HeadlineUiModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(
    private val headlineGetUseCase: HeadlineGetUseCase,
    private val headlineVisitUseCase: HeadlineVisitUseCase
) : BaseViewModel() {

    private val isSuccessFetch = MutableStateFlow<Boolean?>(null)

    init {
        fetchHeadlines()
    }

    val uiState = combine(
        headlineGetUseCase.getAllFlow(),
        isSuccessFetch
    ) { headlines, isSuccessFetch ->
        HeadlineUiState(
            isVisibleFetchFailureMessage = isSuccessFetch == false,
            onClickFetch = ::fetchHeadlines,
            headlines = headlines.toUiModel()
        )
    }.stateIn(
        scope = baseViewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = HeadlineUiState(
            isVisibleFetchFailureMessage = false,
            onClickFetch = ::fetchHeadlines,
            headlines = emptyList()
        )
    )

    private fun fetchHeadlines() {
        baseViewModelScope.launch {
            headlineGetUseCase.fetch()
            isSuccessFetch.value = true
        }
    }

    private fun List<Headline>.toUiModel() = map { it.toUiModel() }

    private fun Headline.toUiModel() = HeadlineUiModel(
        key = key,
        title = title,
        imageUrl = imageUrl,
        publishedDate = publishedAt.toDisplay(),
        highlight = isVisited,
        onClick = { onClickHeadline(this) }
    )

    private fun Date.toDisplay(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(this)
    }

    private fun onClickHeadline(headline: Headline) {
        val navigationCommand = NavigationCommand(
            destination = Destination.WEB_VIEW,
            args = mapOf(
                Key.URL to headline.url,
                Key.TITLE to headline.title
            )
        )
        navigate(navigationCommand)
        baseViewModelScope.launch {
            headlineVisitUseCase.invoke(headline)
        }
    }

    override fun onError(throwable: Throwable): Boolean {
        when (throwable) {
            is HeadlineFetchException -> {
                isSuccessFetch.value = false
            }
        }
        return true
    }
}