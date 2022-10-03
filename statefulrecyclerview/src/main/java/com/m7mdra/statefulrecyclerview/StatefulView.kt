package com.m7mdra.statefulrecyclerview

interface StatefulView {
    fun state(): State
    fun moveToState(newState: State)
    fun showEmpty()
    fun showData()
    fun showError()
    fun showLoading()
    fun showDefault()
}


