package com.globalrescue.domain


sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(val uiComponent: UIComponent) : Resource<T>()
    class Loading<T>(val progressBarState: ProgressBarState = ProgressBarState.Idle) : Resource<T>()
}