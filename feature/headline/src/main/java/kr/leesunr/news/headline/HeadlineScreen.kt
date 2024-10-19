package kr.leesunr.news.headline

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.leesunr.news.core.utils.isTablet
import kr.leesunr.news.headline.ui.HeadlineItem
import kr.leesunr.news.headline.viewmodel.HeadlineViewModel

@Composable
fun HeadlineScreen(
    modifier: Modifier
) {
    val viewModel = hiltViewModel<HeadlineViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    LazyVerticalGrid(
        columns = if(isTablet()) GridCells.Fixed(3) else GridCells.Fixed(1),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = spacedBy(16.dp),
        horizontalArrangement = spacedBy(16.dp),
    ) {
        itemsIndexed(
            items = uiState.headlines,
            key = { _, item -> item.key },
        ) { _, item ->
            HeadlineItem(
                uiModel = item,
            )
        }
    }
}