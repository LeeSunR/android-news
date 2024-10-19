package kr.leesunr.news.headline

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.leesunr.news.headline.ui.HeadlineItem
import kr.leesunr.news.headline.viewmodel.HeadlineViewModel

@Composable
fun HeadlineScreen(
    modifier: Modifier
) {
    val viewModel = hiltViewModel<HeadlineViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = spacedBy(16.dp),
    ) {
        itemsIndexed(
            items = uiState.headlines,
            key = { _, item -> item.key },
        ) { _, item ->
            HeadlineItem(
                modifier = modifier,
                uiModel = item,
            )
        }
    }
}