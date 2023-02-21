package com.globalrescue.domain

sealed class UIComponent {
    data class SnackBar(val message: Int) : UIComponent()
}