package com.m7mdra.statefulrecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class StatefulRecyclerView : FrameLayout, StatefulView {
    private var currentState = State.Default


    private fun onStateChange() {
        when (currentState) {
            State.Data -> {
                hideProgress()
                hideError()
                hideEmpty()
                recyclerView?.visible()
            }
            State.Loading -> {
                showProgress()
                hideError()
                hideEmpty()
                recyclerView?.gone()
            }
            State.Error -> {
                hideProgress()
                showError()
                hideEmpty()
                recyclerView?.gone()
            }
            State.Empty -> {
                hideProgress()
                hideError()
                showEmpty()
                recyclerView?.gone()

            }
            State.Default -> {
                hideProgress()
                hideError()
                hideEmpty()
                recyclerView?.gone()

            }
        }
    }

    private fun showProgress() {
        if (progressView != null) {
            progressView?.visible()
        } else {
            progressBar?.visible()
        }
    }

    private fun showError() {
        if (errorView != null) {
            errorView?.visible()
        } else {
            errorLayout?.visible()
        }
    }

    private fun hideError() {
        if (errorView != null) {
            errorView?.gone()
        } else {
            errorLayout?.gone()
        }
    }

    private fun hideProgress() {
        if (progressView != null) {
            progressView?.gone()
        } else {
            progressBar?.gone()
        }
    }

    private fun showEmpty() {
        if (emptyView != null) {
            emptyView?.visible()
        } else {
            emptyMessageTextView?.visible()
        }
    }

    private fun hideEmpty() {
        if (emptyView != null) {
            emptyView?.gone()
        } else {
            emptyMessageTextView?.gone()
        }
    }

    //progress
    internal var progressBar: CircularProgressIndicator? = null
    internal var progressView: View? = null

    //error
    internal var errorLayout: LinearLayout? = null
    internal var errorMessageTextView: MaterialTextView? = null
    internal var errorRetryButton: MaterialButton? = null
    internal var errorView: View? = null

    internal var emptyMessageTextView: MaterialTextView? = null
    internal var emptyView: View? = null

    internal var recyclerView: RecyclerView? = null

    constructor(context: Context) : super(context) {
        inflate(context, R.layout.stateful_recycler_view_layout, this)

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        inflate(context, R.layout.stateful_recycler_view_layout, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        progressBar = findViewById(R.id.progressBar)
        errorLayout = findViewById(R.id.errorLayout)
        errorMessageTextView = findViewById(R.id.errorMessageTextView)
        errorRetryButton = findViewById(R.id.errorRetryButton)
        emptyMessageTextView = findViewById(R.id.emptyMessageTextView)
        recyclerView = findViewById(R.id.recyclerView)
    }


    override fun state(): State {
        return currentState
    }

    override fun moveToState(newState: State) {
        currentState = newState
        onStateChange()

    }


}