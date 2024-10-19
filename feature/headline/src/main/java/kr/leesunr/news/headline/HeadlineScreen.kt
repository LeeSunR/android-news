package kr.leesunr.news.headline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kr.leesunr.news.core.utils.isTablet
import kr.leesunr.news.core.utils.viewModelProvider
import kr.leesunr.news.headline.ui.HeadlineItem
import kr.leesunr.news.headline.viewmodel.HeadlineViewModel

@Composable
fun HeadlineScreen(
    modifier: Modifier
) {
    val viewModel = viewModelProvider<HeadlineViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    Column(
        modifier = modifier,
    ) {
        if(uiState.isVisibleFetchFailureMessage) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onSurface)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 4.dp,
                    ),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.fetch_failure_message),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.surface,
                )
                Button(
                    modifier = Modifier
                        .padding(0.dp),
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    onClick = uiState.onClickFetch,
                ) {
                    Text(
                        text = stringResource(R.string.refetch_button),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.surface,
                    )
                }
            }
        }

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
}