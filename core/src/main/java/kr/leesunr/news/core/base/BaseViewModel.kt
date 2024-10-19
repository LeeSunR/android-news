package kr.leesunr.news.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus

abstract class BaseViewModel:ViewModel() {
    val baseViewModelScope: CoroutineScope
        get() = viewModelScope.plus(defaultErrorHandler)

    private val defaultErrorHandler = CoroutineExceptionHandler { a, throwable ->
        onError(throwable)
    }

    private fun onError(throwable: Throwable): Boolean {
        return false
    }

}