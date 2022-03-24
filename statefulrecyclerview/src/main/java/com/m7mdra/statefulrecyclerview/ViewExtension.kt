package com.m7mdra.statefulrecyclerview

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView

internal fun View.visible() {
    visibility = View.VISIBLE
}

internal fun View.gone() {
    visibility = View.GONE
}

internal fun View.invisible() {
    visibility = View.INVISIBLE
}

internal inline fun <T : View> T.apply(block: T.() -> Unit): T {
    block()
    return this
}

fun StatefulRecyclerView.progressBar(builder: CircularProgressIndicator.() -> Unit) {
    progressBar?.apply(builder)
}

fun StatefulRecyclerView.emptyText(builder: MaterialTextView.() -> Unit) {
    emptyMessageTextView?.apply(builder)
}


fun StatefulRecyclerView.progressViewBuilder(builder: () -> View) {
    progressView = builder.invoke()
    progressView?.gone()
    addView(progressView)
}

fun StatefulRecyclerView.errorViewBuilder(builder: () -> View) {
    errorView = builder.invoke()
    errorView?.gone()
    addView(errorView)
}

fun StatefulRecyclerView.emptyViewBuilder(builder: () -> View) {
    emptyView = builder.invoke()
    emptyView?.gone()
    addView(emptyView)
}

fun StatefulRecyclerView.errorLayout(builder: LinearLayout.() -> Unit) {
    errorLayout?.apply(builder)
}

fun StatefulRecyclerView.errorMessage(builder: MaterialTextView.() -> Unit) {
    errorMessageTextView?.apply(builder)
}

fun StatefulRecyclerView.errorButton(builder: MaterialButton.() -> Unit) {
    errorRetryButton?.apply(builder)
}

fun StatefulRecyclerView.recyclerView(builder: RecyclerView.() -> Unit) {
    recyclerView?.apply(builder)
}