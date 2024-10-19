package kr.leesunr.news.core.utils

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.booleanResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import kotlinx.coroutines.flow.collectLatest
import kr.leesunr.news.core.R
import kr.leesunr.news.core.base.BaseViewModel
import kr.leesunr.news.core.base.command.Destination
import kr.leesunr.news.core.base.command.Key
import kr.leesunr.news.webview.WebViewActivity

@Composable
fun isTablet(): Boolean {
    return booleanResource(id = R.bool.is_tablet)
}

@Composable
inline fun <reified VM : BaseViewModel> viewModelProvider(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
): VM {
    val viewModel = hiltViewModel<VM>(viewModelStoreOwner)
    viewModel.RegisterEvent()
    return viewModel
}

@Composable
fun BaseViewModel.RegisterEvent() {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        this@RegisterEvent.navigateEvent.collectLatest {
            when (it.destination) {
                Destination.WEB_VIEW -> {
                    val title = it.args[Key.TITLE] as String
                    val url = it.args[Key.URL] as String
                    val intent = Intent(context, WebViewActivity::class.java).apply {
                        putExtra("title", title)
                        putExtra("url", url)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
}
