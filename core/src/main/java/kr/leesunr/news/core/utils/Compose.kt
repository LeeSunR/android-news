package kr.leesunr.news.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.booleanResource
import kr.leesunr.news.core.R

@Composable
fun isTablet(): Boolean {
    return booleanResource(id = R.bool.is_tablet)
}