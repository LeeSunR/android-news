package kr.leesunr.news.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.plus
import kr.leesunr.news.core.base.command.NavigationCommand

abstract class BaseViewModel:ViewModel() {
    private val _navigateEvent = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 1)
    val navigateEvent = _navigateEvent.asSharedFlow()

    val baseViewModelScope: CoroutineScope
        get() = viewModelScope.plus(defaultErrorHandler)

    private val defaultErrorHandler = CoroutineExceptionHandler { a, throwable ->
        onError(throwable)
    }

    fun navigate(command: NavigationCommand) {
        _navigateEvent.tryEmit(command)
    }

    open fun onError(throwable: Throwable): Boolean {
        return false
    }

}